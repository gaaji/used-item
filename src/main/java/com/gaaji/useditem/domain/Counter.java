package com.gaaji.useditem.domain;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counter counter = (Counter) o;
        return suggestCount == counter.suggestCount && interestCount == counter.interestCount
                && viewCount == counter.viewCount && chatCount == counter.chatCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suggestCount, interestCount, viewCount, chatCount);
    }
}
