package com.gaaji.useditem.adaptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveResponse {

    private String authId;
    private String nickname;
    private String pictureUrl;
    private double mannerTemperature;

}
