package com.paperized.productstore.controller;

import com.paperized.productstore.dto.UserDTO;
import com.paperized.productstore.security.util.IsAuthenticated;
import com.paperized.productstore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @IsAuthenticated
  @RequestMapping("/current")
  public UserDTO getCurrentUser(Authentication authentication) {
    return userService.getUserByUsername(authentication.getName());
  }
}
