package com.paperized.productstore.dto;

import com.paperized.productstore.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    public static User toUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreationTime(Timestamp.from(Instant.now()));
        return user;
    }
}
