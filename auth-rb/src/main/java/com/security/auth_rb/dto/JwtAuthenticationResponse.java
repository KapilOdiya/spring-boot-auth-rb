package com.security.auth_rb.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
     private String refreshToken;
}
