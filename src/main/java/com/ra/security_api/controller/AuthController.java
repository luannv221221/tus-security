package com.ra.security_api.controller;

import com.ra.security_api.model.dto.DataResponse;
import com.ra.security_api.model.dto.LoginRequestDTO;
import com.ra.security_api.model.dto.RegisterRequestDTO;
import com.ra.security_api.model.dto.UserResponseDTO;
import com.ra.security_api.model.entity.Role;
import com.ra.security_api.repository.RoleRepository;
import com.ra.security_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication",description = "Manager login, register user")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    @Operation(summary = "Register account for user")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        DataResponse response = authService.register(registerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserResponseDTO userResponseDTO = authService.login(loginRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
