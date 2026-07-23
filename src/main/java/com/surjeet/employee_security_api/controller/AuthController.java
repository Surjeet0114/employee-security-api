package com.surjeet.employee_security_api.controller;

import com.surjeet.employee_security_api.dto.*;
import com.surjeet.employee_security_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @Valid @RequestBody RegisterRequestDto requestDto) {

        return new ResponseEntity<>(
                authService.register(requestDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok(
                authService.login(requestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDto requestDto) {

        return ResponseEntity.ok(
                authService.refreshToken(requestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Valid @RequestBody RefreshTokenRequestDto requestDto) {

        authService.logout(requestDto);

        return ResponseEntity.ok("Logged out successfully.");
    }

}