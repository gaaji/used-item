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

    private String authId;
    private String townId;
    private String address;

    public void injectHeaderInfo(String authId, String townHeader){
        this.authId = authId;
        TownToken townToken = null;
        try {
             townToken = new ObjectMapper().readValue(townHeader, TownToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.townId = townToken.getTownId();
        this.address = townToken.getAddress();
    }
}
