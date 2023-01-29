package com.sanedge.netflixclone.dto.request;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MyListRequest {
    private String title;
    private String type;
    private String genre;
    private ArrayList<String> content;
}
