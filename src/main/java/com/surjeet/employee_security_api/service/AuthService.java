package com.surjeet.employee_security_api.service;

import com.surjeet.employee_security_api.dto.*;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);

    LoginResponseDto login(LoginRequestDto request);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request);

    void logout(RefreshTokenRequestDto request);
}