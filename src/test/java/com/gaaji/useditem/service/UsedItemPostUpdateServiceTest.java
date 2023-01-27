package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gaaji.useditem.adaptor.S3Uploader;
import com.gaaji.useditem.applicationservice.UsedItemPicturesCreateService;
import com.gaaji.useditem.applicationservice.UsedItemPostUpdateService;
import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.ReservationStatusChangePriceException;
import com.gaaji.useditem.impl.FakeUsedItemPostRepository;
import com.gaaji.useditem.impl.StubS3Uploader;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class UsedItemPostUpdateServiceTest {

    // postId로 포스트 조회하기.
    // 포스트의 작성자ID랑 Authorization이랑 같은지 체크, 다르면 Exception.
    // DTO에서 받아온 값 넣어주기.
    // DTO에서 받아온 사진url 리스트 순서대로 설정하기.   //

    UsedItemPostRepository usedItemPostRepository;
    @BeforeEach
    void beforeEach(){

        usedItemPostRepository = new FakeUsedItemPostRepository();
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


        usedItemPostRepository.save(usedItemPost);
        S3Uploader s3Uploader = new StubS3Uploader();

        UsedItemPicturesCreateService usedItemPicturesCreateService
                = new UsedItemPicturesCreateService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";

        //when
        usedItemPicturesCreateService.createPictures(postId, files);
    }



    @Test
    void 정상_생성 () throws Exception{
        //given


        UsedItemPostUpdateService usedItemPostUpdateService = new UsedItemPostUpdateService(usedItemPostRepository);
        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);


        String postId = "foo";
        String authorization = "seller";
        usedItemPostUpdateService.updatePost(postId, authorization,dto);
        //when
        UsedItemPost finded = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();

        //then
        assertThat(finded.getTitle()).isEqualTo("title");
        assertThat(finded.getContents()).isEqualTo("content");
        assertThat(finded.getCategory()).isEqualTo("category");
        assertThat(finded.getPrice()).isEqualTo(10000);
        assertThat(finded.getIsHide()).isFalse();
        assertThat(finded.getCanSuggest()).isFalse();
        assertThat(finded.getPicturesUrl().get(0)).isEqualTo("url1");
        assertThat(finded.getPicturesUrl().get(1)).isEqualTo("url0");
        assertThat(finded.getPicturesUrl().get(2)).isEqualTo("url2");
    }
    @Test
    void 예외_작성자와_아이디가_다름 () throws Exception{
        //given
        UsedItemPostUpdateService usedItemPostUpdateService = new UsedItemPostUpdateService(usedItemPostRepository);
        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);


        String postId = "foo";
        String authorization = "sallah";
        assertThatThrownBy(() -> usedItemPostUpdateService.updatePost(postId, authorization,dto))
                .isInstanceOf(NoMatchAuthIdAndSellerIdException.class);
        
    
    }

    @Test
    void 예외_예약_상태_글_가격_변경 () throws Exception{
        //given
        UsedItemPost finded = usedItemPostRepository.findByPostId(UsedItemPostId.of("foo"))
                .orElseThrow();
        Field tradeStatus = finded.getClass().getDeclaredField("tradeStatus");
        tradeStatus.setAccessible(true);
        tradeStatus.set(finded, TradeStatus.RESERVATION);

        UsedItemPostUpdateService usedItemPostUpdateService = new UsedItemPostUpdateService(usedItemPostRepository);
        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url0");
        urls.add("url2");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);


        String postId = "foo";
        String authorization = "seller";
        assertThatThrownBy(() -> usedItemPostUpdateService.updatePost(postId, authorization,dto))
                .isInstanceOf(ReservationStatusChangePriceException.class);


    }

}