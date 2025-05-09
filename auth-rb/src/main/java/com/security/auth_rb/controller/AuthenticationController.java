package com.security.auth_rb.controller;

import com.security.auth_rb.dto.JwtAuthenticationResponse;
import com.security.auth_rb.dto.RefreshTokenRequest;
import com.security.auth_rb.dto.SignupRequest;
import com.security.auth_rb.jpa.entities.User;
import com.security.auth_rb.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User>  signup(@Valid @RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.Signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signin(signupRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponse> generateRefreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.generateRereshToken(refreshTokenRequest));
    }
}
