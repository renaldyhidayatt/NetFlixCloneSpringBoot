package com.sanedge.netflixclone.service;

import com.sanedge.netflixclone.dto.request.AuthRequest;
import com.sanedge.netflixclone.dto.request.RegisterRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;

public interface AuthService {
    MessageResponse loginUser(AuthRequest authRequest);

    MessageResponse register(RegisterRequest registerRequest);
}
