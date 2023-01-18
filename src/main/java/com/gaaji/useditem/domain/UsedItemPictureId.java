package com.gaaji.useditem.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UsedItemPictureId implements Serializable {

    private String id;

    public static UsedItemPictureId of(String id) {
        return new UsedItemPictureId(id);
    }
}
