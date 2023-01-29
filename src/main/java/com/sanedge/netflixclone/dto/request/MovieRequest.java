package com.sanedge.netflixclone.dto.request;

import lombok.Data;

@Data
public class MovieRequest {
    private String title;
    private String desc;
    private String img;
    private String imgTitle;
    private String trailer;
    private String video;
    private Integer limit;
    private String genre;
    private Boolean isSeries;
}
