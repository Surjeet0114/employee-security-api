package com.surjeet.employee_security_api.controller;

import com.surjeet.employee_security_api.dto.RegisterRequestDto;
import com.surjeet.employee_security_api.dto.RegisterResponseDto;
import com.surjeet.employee_security_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request) {

        RegisterResponseDto response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
