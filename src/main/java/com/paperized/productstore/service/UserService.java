package com.paperized.productstore.service;

import com.paperized.productstore.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {
  UserDTO getUserByEmail(String email);
  UserDTO changeRole(Long id, String role);
}
