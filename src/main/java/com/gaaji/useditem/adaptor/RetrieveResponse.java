package com.gaaji.useditem.adaptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RetrieveResponse {

    private String authId;
    private String nickname;
    private double mannerTemperature;

}
