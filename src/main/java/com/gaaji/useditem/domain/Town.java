package com.gaaji.useditem.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Town {
    private String id;
    private String address;

    public static Town of(String id, String address){
        return new Town(id, address);
    }
}
