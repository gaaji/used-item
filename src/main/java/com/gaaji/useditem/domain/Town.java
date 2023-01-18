package com.gaaji.useditem.domain;

import com.gaaji.useditem.exception.InputNullDataOnAddressException;
import com.gaaji.useditem.exception.InputNullDataOnTownIdException;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Town {
    private String id;
    private String address;

    public static Town of(String id, String address){
        if (!StringUtils.hasText(id))  throw new InputNullDataOnTownIdException();
        if (!StringUtils.hasText(address)) throw new InputNullDataOnAddressException();
        return new Town(id, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Town town = (Town) o;
        return Objects.equals(id, town.id) && Objects.equals(address, town.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
