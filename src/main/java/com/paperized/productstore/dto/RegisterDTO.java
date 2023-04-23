package com.paperized.productstore.dto;

import com.paperized.productstore.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class RegisterDTO {
    private String username;
    private String email;
    private String password;

    public static User toUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setCreationTime(Timestamp.from(Instant.now()));
        return user;
    }
}
