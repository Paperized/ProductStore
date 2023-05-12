package com.paperized.productstore.serviceImpl;

import com.paperized.productstore.dto.UserDTO;
import com.paperized.productstore.entity.User;
import com.paperized.productstore.exception.EntityNotFoundException;
import com.paperized.productstore.repository.RoleRepository;
import com.paperized.productstore.repository.UserRepository;
import com.paperized.productstore.security.util.SecurityUtils;
import com.paperized.productstore.service.UserService;
import com.paperized.productstore.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.paperized.productstore.security.AuthRole.ADMIN;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public UserDTO getUserByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    return MapperUtil.mapTo(user, SecurityUtils.getCurrentRoles(), UserDTO::fullUser);
  }

  @Transactional
  @Override
  public UserDTO changeRole(Long id, String role) {
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    user.getRoles().clear();
    user.getRoles().add(roleRepository.findByName(ADMIN).orElseThrow());
    return MapperUtil.mapTo(userRepository.save(user), null, UserDTO::fullUser);
  }
}
