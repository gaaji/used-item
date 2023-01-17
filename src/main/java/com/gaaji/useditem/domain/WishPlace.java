package com.gaaji.useditem.domain;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WishPlace wishPlace = (WishPlace) o;
        return Objects.equals(placeX, wishPlace.placeX) && Objects.equals(placeY,
                wishPlace.placeY) && Objects.equals(placeText, wishPlace.placeText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeX, placeY, placeText);
    }
}
