package com.sanedge.netflixclone.service;

import com.sanedge.netflixclone.dto.request.MyListRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;

public interface MyListService {
    MessageResponse createMyList(MyListRequest myListRequest);

    MessageResponse deleteMyList(Long id);

    MessageResponse getList(String type, String genre);
}
