package com.paperized.productstore.controller;

import com.paperized.productstore.dto.UserDTO;
import com.paperized.productstore.security.util.IsAuthenticated;
import com.paperized.productstore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @IsAuthenticated
  @GetMapping("/current")
  public UserDTO getCurrentUser(Authentication authentication) {
    return userService.getUserByEmail(authentication.getName());
  }

  @PostMapping("/test-api/{id}/change-role")
  public UserDTO changeRole(@PathVariable Long id, ChangeRoleDTO changeRoleDTO) {
    return userService.changeRole(id, changeRoleDTO.role());
  }

  private record ChangeRoleDTO(String role) { }
}
