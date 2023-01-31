package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.adaptor.AuthServiceClient;
import com.gaaji.useditem.applicationservice.UsedItemPostRetrieveService;
import com.gaaji.useditem.applicationservice.UsedItemPostViewCountIncreaseService;
import com.gaaji.useditem.controller.dto.PostRetrieveResponse;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.impl.FakeUsedItemPostCounterRepository;
import com.gaaji.useditem.impl.FakeUsedItemPostRepository;
import com.gaaji.useditem.impl.StubAuthServiceClient;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import org.junit.jupiter.api.Test;

class UsedItemPostRetrieveServiceTest {


    @Test
    void 정상_글조회() throws Exception {
        //given
        UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of("foo"),
                SellerId.of("foo"), Post.of("foo", "bar", "foobar")
                , Price.of(10000000L), true, WishPlace.of("", "", "")
                , Town.of("foo", "bar"));
        UsedItemPostCounter foo = UsedItemPostCounter.of(UsedItemPostId.of("foo"), Counter.of());


        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        AuthServiceClient authServiceClient = new StubAuthServiceClient();

        usedItemPostRepository.save(usedItemPost);
        usedItemPostCounterRepository.save(foo);


        UsedItemPostViewCountIncreaseService usedItemPostViewCountIncreaseService = new UsedItemPostViewCountIncreaseService(usedItemPostCounterRepository);
        UsedItemPostRetrieveService usedItemPostRetrieveService = new UsedItemPostRetrieveService(
                usedItemPostRepository, authServiceClient, usedItemPostViewCountIncreaseService);

        //when
        PostRetrieveResponse response = usedItemPostRetrieveService.retrievePost("foo",
                "foo");
        //then
        assertThat(response.getPostId()).isEqualTo("foo");
        assertThat(response.getSellerId()).isEqualTo("foo");
        assertThat(response.getIsHide()).isFalse();
        assertThat(response.getCanSuggest()).isTrue();
        assertThat(response.getCategory()).isEqualTo("foobar");
        assertThat(response.getContents()).isEqualTo("bar");
        assertThat(response.getTitle()).isEqualTo("foo");
        assertThat(response.getTownId()).isEqualTo("foo");
        assertThat(response.getTownAddress()).isEqualTo("bar");
        assertThat(response.getWishX()).isEmpty();
        assertThat(response.getWishY()).isEmpty();
        assertThat(response.getWishText()).isEmpty();
        assertThat(response.getPrice()).isEqualTo(10000000L);
        assertThat(response.getViewCount()).isEqualTo(1);
        assertThat(response.getChatCount()).isZero();
        assertThat(response.getSuggestCount()).isZero();
        assertThat(response.getInterestCount()).isZero();
        assertThat(response.getTradeStatus()).isEqualTo(TradeStatus.SELLING);
        assertThat(response.getSellerProfilePictureUrl()).isEqualTo("foo");
    }

}