package com.sanedge.netflixclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.netflixclone.dto.request.MovieRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.service.impl.MovieServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
        MessageResponse messageResponse = this.movieServiceImpl.createMovie(movieRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateMovie(@PathVariable Long id,
            @Valid @RequestBody MovieRequest movieRequest) {
        MessageResponse messageResponse = this.movieServiceImpl.updateMovie(id, movieRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/")
    public ResponseEntity<MessageResponse> findAll() {
        MessageResponse messageResponse = this.movieServiceImpl.findAll();

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/random")
    public ResponseEntity<MessageResponse> findRandom(@RequestParam(required = false) String type) {
        MessageResponse messageResponse = this.movieServiceImpl.getRandom(type);

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        MessageResponse messageResponse = this.movieServiceImpl.findById(id);

        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteMovie(@PathVariable Long id) {
        MessageResponse messageResponse = this.movieServiceImpl.deleteById(id);

        return ResponseEntity.ok(messageResponse);
    }
}
