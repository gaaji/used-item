package com.gaaji.useditem;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.adaptor.S3Uploader;
import com.gaaji.useditem.applicationservice.UsedItemPicturesCreateService;
import com.gaaji.useditem.domain.FakeUsedItemPostRepository;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class UsedItemPicturesCreateServiceTest {


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
                town,
                Collections.emptyList());


        usedItemPostRepository.save(usedItemPost);

    }

    // 서비스는 게시글ID와 멀티파트를 받는다.
    // 멀티파트를 받아서 s3Uploader에게 업로드를 요청한다.
    // s3Uploader에게 Url들을 받는다.
    // 서비스는 게시글ID로 해당 게시글을 조회한다.
    // 게시글에 해당 URL들을 부착한다.
    @Test
    void 정상_작동 () throws Exception{
        //given
        S3Uploader s3Uploader = new StubS3Uploader();

        UsedItemPicturesCreateService usedItemPicturesCreateService
                = new UsedItemPicturesCreateService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";

        //when
        usedItemPicturesCreateService.createPictures(postId, files);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();
        Field pictures = usedItemPost.getClass()
                .getDeclaredField("pictures");
        pictures.setAccessible(true);


        //then
        List<UsedItemPicture> actual = (List<UsedItemPicture>)pictures.get(usedItemPost);
        assertThat(actual.size()).isSameAs(files.length);


    }

}
