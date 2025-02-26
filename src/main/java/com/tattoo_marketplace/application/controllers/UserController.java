package com.tattoo_marketplace.application.controllers;

import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserResponse;
import com.tattoo_marketplace.application.dto.user.UpdateUserRequest;
import com.tattoo_marketplace.application.dto.user.UserResponse;
import com.tattoo_marketplace.domain.entities.models.User;
import com.tattoo_marketplace.application.services.UserService;
import com.tattoo_marketplace.infra.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
//import java.util.List;
import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Tag(name = "User Controller")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Get authenticated user", description = "Get details of the authenticated user.")
    public ResponseEntity<UserResponse> authenticatedUser() {
        UserResponse user = userService.getAuthenticatedUserResponse();

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Get details of the user by id.")
    public ResponseEntity<UserResponse> userById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.INSTANCE.toResponse(user));
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    // @GetMapping
    // @Operation(summary = "Get all users", description = "Get details of all registered users")
    // public ResponseEntity<List<UserResponse>> allUsers() {
    //     List<UserResponse> users = userService.findAll();

    //     return ResponseEntity.status(HttpStatus.OK).body(users);
    // }

    // @PreAuthorize("hasAuthority('ADMIN')")
    // @GetMapping("/role/{roleName}")
    // @Operation(summary = "Get all users by role", description = "Get details of all registered users by role. Must have admin role.")
    // public ResponseEntity<List<UserResponse>> allUsersByRole(@PathVariable Roles roleName) {
    //     List<UserResponse> users = userService.getAllUsersByRole(roleName);
    //     return ResponseEntity.status(HttpStatus.OK).body(users);
    // }

//     @PostMapping("/profile-picture")
//     public ResponseEntity<UserResponse> uploadProfilePicture(@RequestParam("file") MultipartFile file) {
//         throw new NotImplementedException("Not implemented yet");
// //        UserResponse updatedUser = userService.uploadProfilePicture(file);
// //
// //        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
//     }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "Creates a new user")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        RegisterUserResponse registeredUser = userService.register(request);

        return ResponseEntity.ok(registeredUser);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    // deve ser o proprio usuario para editar ele mesmo
    @PutMapping("/{userId}")
    @Operation(summary = "Edit user", description = "Updates user details by id")
    public ResponseEntity<UserResponse> editUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequest request) {
        UserResponse updatedUser = userService.editUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    // deve ser o proprio usuario para poder excluir sua conta
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Deletes a user by id. Must have admin role.")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
