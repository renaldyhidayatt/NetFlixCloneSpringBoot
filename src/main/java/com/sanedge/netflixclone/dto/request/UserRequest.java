package com.sanedge.netflixclone.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String profilePic;
    private boolean isAdmin;

    public boolean getAdmin() {
        return this.isAdmin;
    }
}
