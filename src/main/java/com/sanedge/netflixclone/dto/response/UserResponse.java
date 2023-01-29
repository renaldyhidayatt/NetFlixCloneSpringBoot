package com.sanedge.netflixclone.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private boolean isAdmin;
    private String profilePic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
