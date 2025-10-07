package com.ra.security_api.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataResponse {
    private int httpStatus;
    private String message;
}
