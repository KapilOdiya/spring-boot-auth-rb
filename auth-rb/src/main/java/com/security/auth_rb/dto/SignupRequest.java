package com.security.auth_rb.dto;

import com.security.auth_rb.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SignupRequest{
    private String userName;
    private String email;
    private String password;
//    private Role userRole;

}
