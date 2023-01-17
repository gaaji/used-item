package com.gaaji.useditem.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Counter {

    private int suggestCount;
    private int interestCount;
    private int viewCount;
    private int chatCount;


    public static Counter of() {
        return new Counter();
    }
    public void increaseViewCount(){
        viewCount++;
    }
}
