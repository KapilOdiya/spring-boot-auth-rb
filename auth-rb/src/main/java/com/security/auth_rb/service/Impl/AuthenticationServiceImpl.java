package com.security.auth_rb.service.Impl;

import com.security.auth_rb.dto.JwtAuthenticationResponse;
import com.security.auth_rb.dto.RefreshTokenRequest;
import com.security.auth_rb.dto.SignupRequest;
import com.security.auth_rb.enums.Role;
import com.security.auth_rb.jpa.entities.User;
import com.security.auth_rb.jpa.repository.UserRepository;
import com.security.auth_rb.service.AuthenticationService;
import com.security.auth_rb.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public User Signup(SignupRequest signupRequest) {
        User user = new User();
        user.setUserName(signupRequest.getUserName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(Role.USER);

        return userRepository.save(user);

    }

    @Override
    public JwtAuthenticationResponse signin(SignupRequest signupRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getEmail(),signupRequest.getPassword()));
        User user = userRepository.findByEmail(signupRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return  jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse generateRereshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        String token =null;
        if(jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(),user)){
            token = jwtService.generateToken(user);
        }

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
        return  jwtAuthenticationResponse;

    }

}
