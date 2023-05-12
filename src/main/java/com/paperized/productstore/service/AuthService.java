package com.paperized.productstore.service;

import com.paperized.productstore.dto.auth.LoginRequestDTO;
import com.paperized.productstore.dto.auth.LoginResponseDTO;
import com.paperized.productstore.dto.auth.RegisterRequestDTO;
import com.paperized.productstore.dto.auth.RegisterResponseDTO;

public interface AuthService {
    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
