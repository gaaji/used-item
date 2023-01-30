package com.gaaji.useditem.domain;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.ReservationStatusChangePriceException;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UsedItemPostModifyTest {

    @Test
    void 정상_수정() throws Exception {
        //given

        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", urls);

        String authorization = "seller";

        //when
        usedItemPost.modify(SellerId.of(authorization), dto);
        //then
        assertThat(usedItemPost.getTitle()).isEqualTo(title);
        assertThat(usedItemPost.getContents()).isEqualTo(content);
        assertThat(usedItemPost.getCategory()).isEqualTo(category);
        assertThat(usedItemPost.getPrice()).isEqualTo(updatedPrice);
        assertThat(usedItemPost.getIsHide()).isTrue();
        assertThat(usedItemPost.getCanSuggest()).isTrue();
        assertThat(usedItemPost.getPicturesUrl().get(0)).isEqualTo("url1");
        assertThat(usedItemPost.getPicturesUrl().get(1)).isEqualTo("url0");
        assertThat(usedItemPost.getPicturesUrl().get(2)).isEqualTo("url2");

    }

    @Test
    void 정상_사진_모두_삭제() throws Exception {
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<String> urls = new ArrayList<>();
        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", urls);

        String authorization = "seller";

        usedItemPost.modify(SellerId.of(authorization), dto);
        //then
        assertThat(usedItemPost.getTitle()).isEqualTo(title);
        assertThat(usedItemPost.getContents()).isEqualTo(content);
        assertThat(usedItemPost.getCategory()).isEqualTo(category);
        assertThat(usedItemPost.getPrice()).isEqualTo(updatedPrice);
        assertThat(usedItemPost.getIsHide()).isTrue();
        assertThat(usedItemPost.getCanSuggest()).isTrue();
        assertThat(usedItemPost.getPicturesUrl().size()).isZero();

    }

    @Test
    void 예외_작성자_ID가_다르다() throws Exception {
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", urls);

        String authorization = "sallar";

        //when    //then
        assertThatThrownBy(() -> usedItemPost.modify(SellerId.of(authorization), dto))
                .isInstanceOf(NoMatchAuthIdAndSellerIdException.class);


    }

    @Test
    void 예외_예약중에_가격은_못바꿈() throws Exception {
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", urls);

        String authorization = "seller";

        Field tradeStatus = usedItemPost.getClass().getDeclaredField("tradeStatus");
        tradeStatus.setAccessible(true);
        tradeStatus.set(usedItemPost, TradeStatus.RESERVATION);

        //when    //then
        assertThatThrownBy(() -> usedItemPost.modify(SellerId.of(authorization), dto))
                .isInstanceOf(ReservationStatusChangePriceException.class);
    }

    @Test
    void 정상_새로운_사진이_제_인덱스를_찾아감() throws Exception {
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<UsedItemPicture> newPictures = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            newPictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url1" + i));
        }
        int[] index = new int[]{1, 3};
        //when

        usedItemPost.addPictures(newPictures, index);
        List<String> picturesUrl = usedItemPost.getPicturesUrl();
        //then
        assertThat(picturesUrl.size()).isSameAs(7);
        assertThat(picturesUrl.get(1)).isEqualTo("url10");
        assertThat(picturesUrl.get(2)).isEqualTo("url1");
        assertThat(picturesUrl.get(3)).isEqualTo("url11");
        assertThat(picturesUrl.get(4)).isEqualTo("url2");
    }

    @Test
    void 정상_새로운_사진이_대표_URL로_설정됨() throws Exception {
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<UsedItemPicture> newPictures = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            newPictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url1" + i));
        }
        int[] index = new int[]{0, 3};
        //when

        usedItemPost.addPictures(newPictures, index);

        //then
        assertThat(usedItemPost.getRepresentPictureUrl()).isEqualTo("url10");
    }
    @Test
    void 정상_사진이_전부_삭제될_경우_대표URL이_null() throws Exception {
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", Collections.EMPTY_LIST);
        //when
        usedItemPost.modify(sellerId, dto);


        //then
        assertThat(usedItemPost.getRepresentPictureUrl()).isNull();
    }

    @Test
    void 정상_사진의_순서가_바뀔_경우_대표_URL이_바뀐다() throws Exception {
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        String title = "title";
        String content = "content";
        String category = "category";
        int updatedPrice = 10000;
        boolean updatedHide = true;
        boolean updatedSuggest = true;
        PostUpdateRequest dto = new PostUpdateRequest(title, content, category, updatedPrice,
                updatedHide, updatedSuggest, "", "", "", urls);

        String authorization = "seller";

        //when
        usedItemPost.modify(SellerId.of(authorization), dto);
        //then
        assertThat(usedItemPost.getRepresentPictureUrl()).isEqualTo("url1");
    }

    @Test
    void 예외_인덱스가_범위를_초과함() throws Exception {
        //given
        UsedItemPostId itemPostId = UsedItemPostId.of("foo");
        SellerId sellerId = SellerId.of("seller");
        Post post = Post.of("foo", "bar", "foobar");
        Price price = Price.of(1000L);
        boolean canSuggest = false;
        WishPlace wishPlace = null;
        Town town = Town.of("foo", "bar");
        UsedItemPost usedItemPost = UsedItemPost.of(itemPostId, sellerId, post, price, canSuggest,
                wishPlace,
                town
        );

        List<UsedItemPicture> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url" + i));
        }
        usedItemPost.addPictures(pictures);

        List<UsedItemPicture> newPictures = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            newPictures.add(UsedItemPicture.of(UsedItemPictureId.of(UUID.randomUUID().toString()),
                    "url1" + i));
        }
        int[] index = new int[]{1, 100};
        //when //then
        assertThatThrownBy(() -> usedItemPost.addPictures(newPictures, index))
                .isInstanceOf(IndexOutOfBoundsException.class);


    }


}
