package com.gaaji.useditem.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostUpdateRequest {

    private String title;
    private String contents;
    private String category;
    private long price;
    private Boolean isHide;
    private Boolean canSuggest;
    private String wishX;
    private String wishY;
    private String wishText;
    private List<String> urls;


}
