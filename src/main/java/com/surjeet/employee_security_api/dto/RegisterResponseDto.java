package com.surjeet.employee_security_api.dto;

import com.surjeet.employee_security_api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponseDto {

    private Long id;

    private String username;

    private Role role;

    private String message;
}
