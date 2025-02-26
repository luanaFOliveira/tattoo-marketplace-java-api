package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserResponse;
import com.tattoo_marketplace.application.dto.user.UserResponse;
import com.tattoo_marketplace.application.dto.user.UpdateUserRequest;
import com.tattoo_marketplace.domain.entities.models.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long getAuthenticatedUserId();

    UserResponse getAuthenticatedUserResponse();

    User getAuthenticatedUser();

    List<UserResponse> findAll();

    RegisterUserResponse register(RegisterUserRequest request);

    User getUserById(Long userId);

    UserResponse editUser(Long userId, UpdateUserRequest request);

    void deleteUser(Long userId);
}
