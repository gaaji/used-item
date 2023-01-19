package com.gaaji.useditem.domain;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Access(AccessType.FIELD)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UsedItemPictureId implements Serializable {

    private String id;

    public String getId(){
        return id;
    }

    public static UsedItemPictureId of(String id) {
        return new UsedItemPictureId(id);
    }
}
