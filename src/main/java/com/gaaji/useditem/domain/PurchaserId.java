package com.gaaji.useditem.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PurchaserId {

    private String id;

    public static PurchaserId of(String id) {
        return new PurchaserId(id);
    }
}
