package com.gaaji.useditem.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostCreateRequest {

    private String title;
    private String contents;
    private String category;
    private Boolean canSuggest;
    private Long price;
    private String placeX;
    private String placeY;
    private String placeText;
}
