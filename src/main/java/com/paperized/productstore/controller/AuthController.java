package com.paperized.productstore.controller;

import com.paperized.productstore.dto.auth.LoginRequestDTO;
import com.paperized.productstore.dto.auth.LoginResponseDTO;
import com.paperized.productstore.dto.auth.RegisterRequestDTO;
import com.paperized.productstore.dto.auth.RegisterResponseDTO;
import com.paperized.productstore.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }
}















