package com.ra.security_api.service;

import com.ra.security_api.model.dto.DataResponse;
import com.ra.security_api.model.dto.LoginRequestDTO;
import com.ra.security_api.model.dto.RegisterRequestDTO;
import com.ra.security_api.model.dto.UserResponseDTO;

public interface AuthService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    DataResponse register(RegisterRequestDTO registerRequestDTO);

}
