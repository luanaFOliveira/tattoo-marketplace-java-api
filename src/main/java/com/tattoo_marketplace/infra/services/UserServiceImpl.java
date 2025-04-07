package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.user.PasswordValidation;
import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserResponse;
import com.tattoo_marketplace.application.dto.user.UpdateUserRequest;
import com.tattoo_marketplace.application.dto.user.UserResponse;
import com.tattoo_marketplace.domain.entities.models.User;
import com.tattoo_marketplace.infra.mappers.UserMapper;
import com.tattoo_marketplace.domain.repository.UserRepository;
import com.tattoo_marketplace.application.services.UserService;
import com.tattoo_marketplace.application.services.ImageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImageService imageService;


    @Override
    public Long getAuthenticatedUserId() {
        Authentication authentication = getAuthentication();

        return ((User) authentication.getPrincipal()).getId();
    }

    @Override
    public UserResponse getAuthenticatedUserResponse() {
        Authentication authentication = getAuthentication();

        return userMapper.toResponse((User) authentication.getPrincipal());
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = getAuthentication();

        return (User) authentication.getPrincipal();
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationCredentialsNotFoundException("No authenticated user found");
        }
        return authentication;
    }

    @Override
    public List<UserResponse> findAll() {
        return userMapper.toResponses(userRepository.findAll());
    }


    private void assignPassword(User user, String password) {
        final var encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    }

    private void saveProfileImage(User user, MultipartFile profilePicture){
        try {
            String imageUrl = imageService.saveImage(profilePicture);
            user.setProfilePicture(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest request, MultipartFile profilePicture) {
        assertPasswordsMatch(request);

        User user = userMapper.fromRegisterRequest(request);

        assignPassword(user, request.getPassword());
        saveProfileImage(user, profilePicture);
        
        return userMapper.toRegisterResponse(userRepository.save(user));
    }

    private void assertPasswordsMatch(PasswordValidation request) {
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find user for id=%s", userId)));
    }

    @Override
    public UserResponse editUser(Long userId, UpdateUserRequest request, MultipartFile profilePicture) {
        Long authenticatedUserId = getAuthenticatedUserId();

        if (!authenticatedUserId.equals(userId)) {
            throw new RuntimeException("You can only edit your own profile");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserPartial(user,request);
        if (profilePicture != null) {
            saveProfileImage(user, profilePicture);
        }
        if (request.getPassword() != null && request.getPasswordConfirm() != null) {
            assertPasswordsMatch(request);
            assignPassword(user, request.getPassword());
        }


        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        Long authenticatedUserId = getAuthenticatedUserId();
        
        if (!authenticatedUserId.equals(userId)) {
            throw new RuntimeException("You can only delete your own profile");
        }

        userRepository.deleteById(userId);
    }
}
