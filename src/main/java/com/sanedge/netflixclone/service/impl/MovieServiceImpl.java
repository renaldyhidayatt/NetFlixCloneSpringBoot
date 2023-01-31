package com.sanedge.netflixclone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.sanedge.netflixclone.dto.request.MovieRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.exception.NotFoundException;
import com.sanedge.netflixclone.models.Movie;
import com.sanedge.netflixclone.models.User;
import com.sanedge.netflixclone.repository.MovieRepository;
import com.sanedge.netflixclone.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, UserServiceImpl userServiceImpl) {
        this.movieRepository = movieRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public MessageResponse findAll() {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            List<Movie> movies = this.movieRepository.findAll();

            return MessageResponse.builder().message("Berhasil mendapatkan data").data(movies).statusCode(200).build();
        }
        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }

    @Override
    public MessageResponse createMovie(MovieRequest movieRequest) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            try {
                Movie movie2 = new Movie();
                movie2.setTitle(movieRequest.getTitle());
                movie2.setDescription(movieRequest.getDesc());
                movie2.setImg(movieRequest.getImg());
                movie2.setImgTitle(movieRequest.getImgTitle());
                movie2.setTrailer(movieRequest.getTrailer());
                movie2.setVideo(movieRequest.getVideo());
                movie2.setMylimit(movieRequest.getLimit());
                movie2.setGenre(movieRequest.getGenre());
                movie2.setSeries(movieRequest.getIsSeries());

                this.movieRepository.save(movie2);

                return MessageResponse.builder().message("Berhasil mendapatkan data").data(movie2).statusCode(200)
                        .build();
            } catch (Exception e) {
                return MessageResponse.builder().message(e.toString()).statusCode(400).build();
            }
        }

        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }

    @Override
    public MessageResponse updateMovie(Long id, MovieRequest movieRequest) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            try {
                Movie movie = this.movieRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Berhasil mendapatkan data"));
                movie.setTitle(movieRequest.getTitle());
                movie.setDescription(movieRequest.getDesc());
                movie.setImg(movieRequest.getImg());
                movie.setImgTitle(movieRequest.getImgTitle());
                movie.setTrailer(movieRequest.getTrailer());
                movie.setVideo(movieRequest.getVideo());
                movie.setMylimit(movieRequest.getLimit());
                movie.setGenre(movieRequest.getGenre());
                movie.setSeries(movieRequest.getIsSeries());

                this.movieRepository.save(movie);

                return MessageResponse.builder().message("Berhasil mengupdate data").data(movie).statusCode(200)
                        .build();

            } catch (Exception e) {
                return MessageResponse.builder().message(e.toString()).statusCode(400).build();
            }
        }

        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }

    @Override
    public MessageResponse getRandom(String type) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            if (type.equals("series")) {
                List<Movie> movies = this.movieRepository.findRandomSeries();

                return MessageResponse.builder().message("Berhasil mendapatkan data").data(movies).statusCode(200)
                        .build();
            } else {
                List<Movie> movies = this.movieRepository.findRandomMovie();

                return MessageResponse.builder().message("Berhasil mendapatkan data").data(movies).statusCode(200)
                        .build();
            }
        }

        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }

    @Override
    public MessageResponse findById(Long id) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            Movie movie = this.movieRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Berhasil mendapatkan data"));

            return MessageResponse.builder().message("Berhasil menghapus data").data(movie).statusCode(200)
                    .build();
        }
        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }

    @Override
    public MessageResponse deleteById(Long id) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            Movie movie = this.movieRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Berhasil mendapatkan data"));
            this.movieRepository.delete(movie);

            return MessageResponse.builder().message("Berhasil menghapus data").data(null).statusCode(200)
                    .build();

        }
        return MessageResponse.builder().message("Bukan user admin").statusCode(400).build();
    }
}
