package com.ra.security_api.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDTO {
    private String username;
    private String fullName;
    private String password;
}
