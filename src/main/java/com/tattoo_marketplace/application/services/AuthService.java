package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.user.LoginRequest;
import com.tattoo_marketplace.application.dto.user.LoginResponse;

public interface AuthService {

    LoginResponse authenticate(LoginRequest request);

}
