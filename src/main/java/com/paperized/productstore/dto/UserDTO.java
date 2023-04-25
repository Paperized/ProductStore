package com.paperized.productstore.dto;

import com.paperized.productstore.entity.Role;
import com.paperized.productstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  private String username;
  private String email;
  private String[] authorities;
  private String firstName;
  private String lastName;

  public static UserDTO fromUser(User user) {
    return new UserDTO(
      user.getUsername(),
      user.getEmail(),
      user.getRoles().stream().map(Role::getName).toArray(String[]::new),
      null,
      null
    );
  }
}
