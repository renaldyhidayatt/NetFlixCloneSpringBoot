package com.sanedge.netflixclone.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "imgTitle", nullable = true)
    private String imgTitle;

    @Column(name = "imgSm", nullable = true)
    private String imgSm;

    @Column(name = "trailer", nullable = true)
    private String trailer;

    @Column(name = "video", nullable = true)
    private String video;

    @Column(name = "year", nullable = true)
    private String year;

    @Column(name = "mylimit", nullable = true)
    private Integer mylimit;

    @Column(name = "genre", nullable = true)
    private String genre;

    @Column(name = "isSeries", nullable = false)
    private boolean isSeries;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
