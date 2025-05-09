package com.security.auth_rb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    @GetMapping("/heyadmin")
    public ResponseEntity<String> heyAdmin(){
        return ResponseEntity.ok("Hi Admin ... Login");
    }
}
