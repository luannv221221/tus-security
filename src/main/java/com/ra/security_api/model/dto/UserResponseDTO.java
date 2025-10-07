package com.ra.security_api.model.dto;

import com.ra.security_api.model.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String username;
    private String fullName;
    private String typeToken;
    private String token;
    private Set<Role> roles;
}
