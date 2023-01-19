package com.gaaji.useditem;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.PurchaserId;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import java.lang.reflect.Field;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UsedItemPictureTest {


    @Test
    void 정상_생성 () throws Exception{
        //given
        String expected = "foo";

        //when
        UsedItemPicture picture = UsedItemPicture.of(UsedItemPictureId.of(expected), "url");
        //then
        assertThat(picture).isNotNull();
        assertThat(picture.getUsedItemPictureId()).isEqualTo(expected);

    }

    @Test
    void 연관관계_테스트 () throws Exception{
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest, wishPlace,
                town,
                Collections.emptyList());
        UsedItemPicture picture = UsedItemPicture.of(UsedItemPictureId.of("foo"), "url");

        //when
        picture.associateWithPost(usedItemPost);
        Field postField = picture.getClass().getDeclaredField("post");
        postField.setAccessible(true);
        //then
        assertThat(postField.get(picture)).isEqualTo(usedItemPost);
    }

}
