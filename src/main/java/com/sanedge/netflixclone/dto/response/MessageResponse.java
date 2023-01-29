package com.sanedge.netflixclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageResponse {
    private String message;
    private Integer statusCode;
    private Object data;
}
