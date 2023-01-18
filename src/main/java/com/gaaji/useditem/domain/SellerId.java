package com.gaaji.useditem.domain;

import com.gaaji.useditem.exception.InputNullDataOnSellerIdException;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class SellerId {

    private String id;

    public static SellerId of(String id) {
        if(!StringUtils.hasText(id)) throw new InputNullDataOnSellerIdException();
        return new SellerId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellerId sellerId = (SellerId) o;
        return Objects.equals(id, sellerId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
