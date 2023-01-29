package com.sanedge.netflixclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.netflixclone.dto.request.MyListRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.service.impl.MyListServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mylist")
public class MyListController {

    @Autowired
    private MyListServiceImpl myListServiceImpl;

    @GetMapping("/")
    public ResponseEntity<MessageResponse> findAll(@RequestParam(required = false) String type,
            @RequestParam(required = false) String genre) {
        MessageResponse messageResponse = this.myListServiceImpl.getList(type, genre);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody MyListRequest myListRequest) {
        MessageResponse messageResponse = this.myListServiceImpl.createMyList(myListRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        MessageResponse messageResponse = this.myListServiceImpl.deleteMyList(id);

        return ResponseEntity.ok(messageResponse);
    }
}
