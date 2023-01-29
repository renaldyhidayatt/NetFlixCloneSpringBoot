package com.sanedge.netflixclone.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
