package com.gaaji.useditem.domain;

import com.gaaji.useditem.exception.InputNullDataOnPostIdException;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;

import javax.persistence.Access;
import javax.persistence.AccessType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Access(AccessType.FIELD)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UsedItemPostId implements Serializable {

    private String id;

    public String getId(){
        return id;
    }

    public static UsedItemPostId of(String id) {
        if(!StringUtils.hasText(id)) throw new InputNullDataOnPostIdException();
        return new UsedItemPostId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsedItemPostId that = (UsedItemPostId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
