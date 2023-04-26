package com.paperized.productstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConstraintNamesMapper {

  @Bean(name = "constraintsTranslator")
  public Map<String, String> getConstraintNamesTranslator() {
    Map<String, String> map = new HashMap<>();
    // User
    map.put("users.uc_user_username", "USER_USERNAME");
    map.put("users.uc_user_email", "USER_EMAIL");

    // Role
    map.put("roles.uc_role_name", "ROLE_NAME");

    // User-Role
    map.put("user_roles.fk_userroles_user_id", "UR_USER_ID");
    map.put("user_roles.fk_userroles_role_id", "UR_ROLE_ID");

    // Product

    return map;
  }
}
