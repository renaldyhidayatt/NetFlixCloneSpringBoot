package com.sanedge.netflixclone.service;

import java.util.List;

import com.sanedge.netflixclone.dto.request.UserRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.dto.response.StatResponse;
import com.sanedge.netflixclone.models.User;

public interface UserService {
    User getCurrentUser();

    MessageResponse findAll();

    MessageResponse findById(Long id);

    List<StatResponse> statResponse();

    MessageResponse create(UserRequest userRequest);

    MessageResponse findByIdUpdate(Long id, UserRequest userRequest);

    MessageResponse deleteById(Long id);
}
