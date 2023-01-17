package com.gaaji.useditem.domain;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.exception.InputNullDataOnAddressException;
import com.gaaji.useditem.exception.InputNullDataOnCategoryException;
import com.gaaji.useditem.exception.InputNullDataOnPostIdException;
import com.gaaji.useditem.exception.InputNullDataOnPriceException;
import com.gaaji.useditem.exception.InputNullDataOnSellerIdException;
import com.gaaji.useditem.exception.InputNullDataOnTitleException;
import com.gaaji.useditem.exception.InputNullDataOnTownIdException;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UsedItemPostTest {

    
    @Test
    void 정상_생성 () throws Exception{
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        PurchaserId purchaserId = PurchaserId.of(null);
        Town town = Town.of("foo", "bar");

        //when
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest, wishPlace,
                purchaserId, town,
                Collections.emptyList());

        //then
        assertThat(usedItemPost).isNotNull();

    }

    @Test
    void 예외_글ID_존재X () throws Exception{
        //when
        assertThatThrownBy(
                () ->  UsedItemPostId.of(null))
                .isInstanceOf(InputNullDataOnPostIdException.class);

        
        //then
    }
    @Test
    void 예외_판매자ID_존재X () throws Exception{

        assertThatThrownBy(
                () ->  SellerId.of(""))
                .isInstanceOf(InputNullDataOnSellerIdException.class);

    }
    @Test
    void 예외_게시글_제목_존재X () throws Exception{
        assertThatThrownBy(
                () ->  Post.of("","","foo"))
                .isInstanceOf(InputNullDataOnTitleException.class);
    }
    @Test
    void 예외_게시글_카테고리_존재X () throws Exception{
        assertThatThrownBy(
                () ->  Post.of("foo","",null))
                .isInstanceOf(InputNullDataOnCategoryException.class);
    }
    @Test
    void 예외_가격_존재X_Null () throws Exception{
        assertThatThrownBy(
                () ->  Price.of(null))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }
    @Test
    void 예외_가격_존재X_음수 () throws Exception{
        assertThatThrownBy(
                () ->  Price.of(-1000L))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }

    @Test
    void 예외_동네ID_존재X () throws Exception{
        assertThatThrownBy(
                () ->  Town.of(null, "foo"))
                .isInstanceOf(InputNullDataOnTownIdException.class);
    }
    @Test
    void 예외_동네주소_존재X () throws Exception{
        assertThatThrownBy(
                () ->  Town.of("foo", null))
                .isInstanceOf(InputNullDataOnAddressException.class);
    }
}