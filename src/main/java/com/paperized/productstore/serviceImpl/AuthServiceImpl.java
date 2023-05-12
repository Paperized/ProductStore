package com.paperized.productstore.serviceImpl;

import com.paperized.productstore.dto.auth.LoginRequestDTO;
import com.paperized.productstore.dto.auth.LoginResponseDTO;
import com.paperized.productstore.dto.auth.RegisterRequestDTO;
import com.paperized.productstore.dto.auth.RegisterResponseDTO;
import com.paperized.productstore.entity.Role;
import com.paperized.productstore.entity.User;
import com.paperized.productstore.repository.RoleRepository;
import com.paperized.productstore.repository.UserRepository;
import com.paperized.productstore.security.AuthRole;
import com.paperized.productstore.security.JwtService;
import com.paperized.productstore.service.AuthService;
import com.paperized.productstore.util.MapperUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User user = MapperUtil.mapTo(registerRequestDTO, RegisterRequestDTO::toUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findByName(AuthRole.USER).orElseThrow(RuntimeException::new);
        user.getRoles().add(role);

        return new RegisterResponseDTO(userRepository.save(user).getId());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

      User user = (User) authentication.getPrincipal();
      return new LoginResponseDTO(jwtService.generateToken(user));
    }
}
