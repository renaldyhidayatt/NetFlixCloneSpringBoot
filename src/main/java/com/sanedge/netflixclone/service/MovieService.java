package com.sanedge.netflixclone.service;

import com.sanedge.netflixclone.dto.request.MovieRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;

public interface MovieService {
    MessageResponse findAll();

    MessageResponse createMovie(MovieRequest movieRequest);

    MessageResponse updateMovie(Long id, MovieRequest movieRequest);

    MessageResponse findById(Long id);

    MessageResponse getRandom(String type);

    MessageResponse deleteById(Long id);
}
