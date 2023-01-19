package com.gaaji.useditem.domain;

import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Access(AccessType.FIELD)
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

    public String getUsedItemPostIdToString() {
        return usedItemPostId.getId();
    }
    public void increaseViewCount(){
        counter.increaseViewCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsedItemPostCounter that = (UsedItemPostCounter) o;
        return Objects.equals(usedItemPostId, that.usedItemPostId)
                && Objects.equals(counter, that.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedItemPostId, counter);
    }
}
