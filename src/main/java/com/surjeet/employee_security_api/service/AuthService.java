package com.surjeet.employee_security_api.service;

import com.surjeet.employee_security_api.dto.LoginRequestDto;
import com.surjeet.employee_security_api.dto.LoginResponseDto;
import com.surjeet.employee_security_api.dto.RegisterRequestDto;
import com.surjeet.employee_security_api.dto.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

}