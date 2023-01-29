package com.sanedge.netflixclone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.netflixclone.dto.request.UserRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.dto.response.StatResponse;
import com.sanedge.netflixclone.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public ResponseEntity<MessageResponse> findAll() {
        MessageResponse messageResponse = this.userServiceImpl.findAll();

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        MessageResponse messageResponse = this.userServiceImpl.findById(id);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody UserRequest userRequest) {
        MessageResponse messageResponse = this.userServiceImpl.create(userRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> findByUpdate(@PathVariable Long id, UserRequest userRequest) {
        MessageResponse messageResponse = this.userServiceImpl.findByIdUpdate(id, userRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        MessageResponse messageResponse = this.userServiceImpl.deleteById(id);

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<StatResponse>> stats() {
        List<StatResponse> stat = this.userServiceImpl.statResponse();

        return ResponseEntity.ok(stat);
    }

}
