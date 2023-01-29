package com.sanedge.netflixclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanedge.netflixclone.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByIsSeries(boolean isSeries);

    @Query(value = "SELECT m FROM Movie m WHERE m.isSeries = true ORDER BY RAND()")
    List<Movie> findRandomSeries();

    @Query(value = "SELECT m FROM Movie m WHERE m.isSeries = false ORDER BY RAND()")
    List<Movie> findRandomMovie();
}
