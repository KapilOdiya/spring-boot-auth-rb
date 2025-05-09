package com.security.auth_rb.service;

import com.security.auth_rb.dto.JwtAuthenticationResponse;
import com.security.auth_rb.dto.RefreshTokenRequest;
import com.security.auth_rb.dto.SignupRequest;
import com.security.auth_rb.jpa.entities.User;

public interface AuthenticationService {
    User Signup(SignupRequest signupRequest);

    JwtAuthenticationResponse signin(SignupRequest signupRequest);

    JwtAuthenticationResponse generateRereshToken(RefreshTokenRequest refreshTokenRequest);
}
