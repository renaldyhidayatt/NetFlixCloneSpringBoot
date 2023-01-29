package com.sanedge.netflixclone.service.impl;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanedge.netflixclone.dto.request.AuthRequest;
import com.sanedge.netflixclone.dto.request.RegisterRequest;
import com.sanedge.netflixclone.dto.response.AuthResponse;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.dto.response.RegisterResponse;
import com.sanedge.netflixclone.models.User;
import com.sanedge.netflixclone.repository.UserRepository;
import com.sanedge.netflixclone.security.JwtProvider;
import com.sanedge.netflixclone.security.UserDetail;

@Service
public class AuthServiceImpl {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public MessageResponse loginUser(AuthRequest authRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateAccessToken(authentication);

        long expiresAt = jwtProvider.getjwtExpirationMs();
        Date date = new Date();
        date.setTime(expiresAt);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();

        AuthResponse authResponse = AuthResponse.builder().authenticationToken(jwt).expiresAt(date.toString())
                .username(userDetails.getUsername()).build();

        return MessageResponse.builder().message("Berhasil login").data(authResponse).statusCode(200).build();
    }

    public MessageResponse register(RegisterRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        this.userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse(user.getUsername(), user.getEmail());

        return MessageResponse.builder().message("Berhasil register").data(registerResponse).statusCode(200).build();
    }
}
