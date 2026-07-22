package com.surjeet.employee_security_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RefreshTokenResponseDto {

    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer";
}
