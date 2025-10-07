package com.ra.security_api.service.impl;

import com.ra.security_api.model.dto.DataResponse;
import com.ra.security_api.model.dto.LoginRequestDTO;
import com.ra.security_api.model.dto.RegisterRequestDTO;
import com.ra.security_api.model.dto.UserResponseDTO;
import com.ra.security_api.model.entity.Role;
import com.ra.security_api.model.entity.User;
import com.ra.security_api.repository.RoleRepository;
import com.ra.security_api.repository.UserRepository;
import com.ra.security_api.security.UserPrinciple;
import com.ra.security_api.security.jwt.JwtProvider;
import com.ra.security_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // kiem tra thong tin xem ddunsg day khong
        Authentication authentication;
        authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword())
        );
        UserPrinciple  userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return UserResponseDTO.builder()
                .username(userPrinciple.getUsername())
                .fullName(userPrinciple.getUsername())
                .token(jwtProvider.generateToken(userPrinciple))
                .typeToken("Bearer")
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }

    @Override
    public DataResponse register(RegisterRequestDTO registerRequestDTO) {
        // gan quyen cho tai khoan dang kys macj dich quyen la USER
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findRoleByName("USER");
        roles.add(role);
        // conver DTO => ENTITY
        User user = User.builder()
                .username(registerRequestDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .fullName(registerRequestDTO.getFullName())
                .status(true)
                .build();
        userRepository.save(user);
        return new DataResponse(201,"User registered successfully");
    }
}
