package com.gaaji.useditem.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class WishPlace {

    private String placeX;
    private String placeY;
    private String placeText;

    public static WishPlace of(String placeX, String placeY, String placeText) {
        return new WishPlace(placeX,placeY,placeText);
    }
}
