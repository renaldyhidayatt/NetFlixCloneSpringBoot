package com.sanedge.netflixclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.netflixclone.dto.request.AuthRequest;
import com.sanedge.netflixclone.dto.request.RegisterRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.service.impl.AuthServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        MessageResponse messageResponse = this.authServiceImpl.loginUser(authRequest);

        return ResponseEntity.ok(messageResponse);

    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse messageResponse = this.authServiceImpl.register(registerRequest);

        return ResponseEntity.ok(messageResponse);
    }
}
