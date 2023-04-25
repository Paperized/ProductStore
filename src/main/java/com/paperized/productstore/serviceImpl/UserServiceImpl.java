package com.paperized.productstore.serviceImpl;

import com.paperized.productstore.dto.UserDTO;
import com.paperized.productstore.entity.User;
import com.paperized.productstore.exception.EntityNotFoundException;
import com.paperized.productstore.repository.UserRepository;
import com.paperized.productstore.service.UserService;
import com.paperized.productstore.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO getUserByUsername(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    return MapperUtil.mapTo(UserDTO::fromUser, user);
  }
}
