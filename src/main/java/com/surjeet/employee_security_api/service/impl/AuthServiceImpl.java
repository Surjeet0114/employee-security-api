package com.surjeet.employee_security_api.service.impl;

import com.surjeet.employee_security_api.dto.RegisterRequestDto;
import com.surjeet.employee_security_api.dto.RegisterResponseDto;
import com.surjeet.employee_security_api.entity.User;
import com.surjeet.employee_security_api.exception.UserAlreadyExistsException;
import com.surjeet.employee_security_api.repository.UserRepository;
import com.surjeet.employee_security_api.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surjeet.employee_security_api.dto.LoginRequestDto;
import com.surjeet.employee_security_api.dto.LoginResponseDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.surjeet.employee_security_api.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("Username already exists.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .message("User registered successfully.")
                .build();
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                requestDto.getUsername(),
                                requestDto.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return LoginResponseDto.builder()
                .token(token)
                .build();
    }

}