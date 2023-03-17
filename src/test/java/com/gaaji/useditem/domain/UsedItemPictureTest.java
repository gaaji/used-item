package com.gaaji.useditem.domain;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
                town
        );
        UsedItemPicture picture = UsedItemPicture.of(UsedItemPictureId.of("foo"), "url");

        //when
        picture.associateWithPost(usedItemPost,0);
        Field postField = picture.getClass().getDeclaredField("post");
        postField.setAccessible(true);

        Field order = picture.getClass().getDeclaredField("order");
        order.setAccessible(true);

        //then
        assertThat(postField.get(picture)).isEqualTo(usedItemPost);
        assertThat(order.get(picture)).isSameAs(0);
    }

    
    @Test
    void Url비교_테스트 () throws Exception{
        //given
        String url = "bar";

        //when
        UsedItemPicture usedItemPicture = UsedItemPicture.of(UsedItemPictureId.of("foo"), url);
        //then
        assertThat(usedItemPicture).isEqualTo(url);
    
    }

    @Test
    void 리스트_Url비교_테스트 () throws Exception{
        //given
        String url = "bar";
        List<UsedItemPicture> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(UsedItemPicture.of(UsedItemPictureId.of("foo"), url + i));
        }


        //when
        String o = url + "0";
        boolean contains = list.contains(o);
        //then
        assertThat(list.get(0).equals(o)).isTrue();


    }

}
