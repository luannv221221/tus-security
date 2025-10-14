package com.ra.security_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDTO {
    @Schema(description = "usename user",example = "Nguyen Van A")
    @NotBlank(message = "fullName not null")
    private String username;
    @Schema(description = "fullName ",example = "nguyenvana")
    @NotBlank(message = "username not null")
    private String fullName;
    @Schema(description = "password",defaultValue = "123456")
    @NotBlank(message = "password not null")
    @Size(min = 6,message = "password not invalid (min 6 character)")
    private String password;
}
