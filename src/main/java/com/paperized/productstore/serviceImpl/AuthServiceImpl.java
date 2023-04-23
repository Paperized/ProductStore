package com.paperized.productstore.serviceImpl;

import com.paperized.productstore.dto.LoginDTO;
import com.paperized.productstore.dto.RegisterDTO;
import com.paperized.productstore.entity.Role;
import com.paperized.productstore.entity.User;
import com.paperized.productstore.exception.EntityAlreadyExistsException;
import com.paperized.productstore.repository.RoleRepository;
import com.paperized.productstore.repository.UserRepository;
import com.paperized.productstore.security.AuthRole;
import com.paperized.productstore.security.JwtService;
import com.paperized.productstore.service.AuthService;
import com.paperized.productstore.util.MapperUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Long register(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername()))
            throw new EntityAlreadyExistsException("Username already exists!");

        User user = MapperUtil.mapTo(RegisterDTO::toUser, registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findByName(AuthRole.USER).orElseThrow();
        user.getRoles().add(role);

        return userRepository.save(user).getId();
    }

    @Override
    public String login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        UserDetails userDetails = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow();
        return jwtService.generateToken(userDetails);
    }
}
