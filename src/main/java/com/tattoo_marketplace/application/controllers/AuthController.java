package com.tattoo_marketplace.application.controllers;

import com.tattoo_marketplace.application.dto.user.LoginRequest;
import com.tattoo_marketplace.application.dto.user.LoginResponse;
import com.tattoo_marketplace.application.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Auth Controller")
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

   
    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Logs-in with e-mail and password.")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {

        LoginResponse loginResponse = authService.authenticate(request);

        return ResponseEntity.ok(loginResponse);
    }

}
