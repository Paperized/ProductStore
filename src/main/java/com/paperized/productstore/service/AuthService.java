package com.paperized.productstore.service;

import com.paperized.productstore.dto.LoginRequest;
import com.paperized.productstore.dto.LoginResponse;
import com.paperized.productstore.dto.RegisterRequest;
import com.paperized.productstore.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
