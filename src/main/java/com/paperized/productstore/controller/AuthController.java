package com.paperized.productstore.controller;

import com.paperized.productstore.dto.LoginDTO;
import com.paperized.productstore.dto.RegisterDTO;
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
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/register")
    public Long register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }
}















