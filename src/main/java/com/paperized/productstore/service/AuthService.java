package com.paperized.productstore.service;

import com.paperized.productstore.dto.LoginDTO;
import com.paperized.productstore.dto.RegisterDTO;

public interface AuthService {
    Long register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
}
