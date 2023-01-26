package com.gaaji.useditem.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WishPlaceTest {

    @Test
    void NullTest () throws Exception{
        //given

        WishPlace of = WishPlace.of(null, null, null);
        //when
        Assertions.assertThat(of).isNull();
        //then


    }

}