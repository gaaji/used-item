package com.gaaji.useditem.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UsedItemPostCounter {

    @EmbeddedId
    private UsedItemPostId usedItemPostId;

    @Embedded
    private  Counter counter;


    public static UsedItemPostCounter of(UsedItemPostId usedItemPostId,Counter counter){
        return new UsedItemPostCounter(usedItemPostId,counter);
    }

    public void increaseViewCount(){
        counter.increaseViewCount();
    }

}
