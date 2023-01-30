package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.adaptor.S3Uploader;
import com.gaaji.useditem.applicationservice.UsedItemPicturesUploadService;
import com.gaaji.useditem.exception.BothSizeDoseNotMatchedException;
import com.gaaji.useditem.impl.FakeUsedItemPostRepository;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.impl.StubS3Uploader;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class UsedItemPicturesUploadServiceTest {


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

        UsedItemPicturesUploadService usedItemPicturesUploadService
                = new UsedItemPicturesUploadService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";

        //when
        usedItemPicturesUploadService.createPictures(postId, files);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();
        Field pictures = usedItemPost.getClass()
                .getDeclaredField("pictures");
        pictures.setAccessible(true);


        //then
        List<UsedItemPicture> actual = (List<UsedItemPicture>)pictures.get(usedItemPost);
        assertThat(actual.size()).isSameAs(files.length);
    }
    
    @Test
    void 정상_수정_완료 () throws Exception{
        //given
        S3Uploader s3Uploader = new StubS3Uploader();

        UsedItemPicturesUploadService usedItemPicturesUploadService
                = new UsedItemPicturesUploadService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";

        //when
        usedItemPicturesUploadService.createPictures(postId, files);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();


        MultipartFile[] newFiles = new MultipartFile[5];
        int[] indexes = new int[]{5,6,7,8,9};

        usedItemPicturesUploadService.createPictures(postId,newFiles,indexes);
        //when
        List<String> picturesUrl = usedItemPost.getPicturesUrl();

        //then
        assertThat(picturesUrl.size()).isSameAs(10);
    }
    
    @Test
    void 예외_인덱스와_파일개수가_틀림 () throws Exception{
        S3Uploader s3Uploader = new StubS3Uploader();

        UsedItemPicturesUploadService usedItemPicturesUploadService
                = new UsedItemPicturesUploadService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";


        usedItemPicturesUploadService.createPictures(postId, files);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();


        MultipartFile[] newFiles = new MultipartFile[5];
        int[] indexes = new int[]{5,6};
        //when
        //then
        assertThatThrownBy(() -> usedItemPicturesUploadService.createPictures(postId,newFiles,indexes))
                .isInstanceOf(BothSizeDoseNotMatchedException.class);

        
    
    }
    
    @Test
    void 예외_인덱스가_범위를_초과함 () throws Exception{
        S3Uploader s3Uploader = new StubS3Uploader();

        UsedItemPicturesUploadService usedItemPicturesUploadService
                = new UsedItemPicturesUploadService(s3Uploader, usedItemPostRepository);

        MultipartFile[] files = new MultipartFile[5];
        String postId = "foo";


        usedItemPicturesUploadService.createPictures(postId, files);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();


        MultipartFile[] newFiles = new MultipartFile[5];
        int[] indexes = new int[]{100,6,1,2,3};
        //when
        //then
        assertThatThrownBy(() -> usedItemPicturesUploadService.createPictures(postId,newFiles,indexes))
                .isInstanceOf(IndexOutOfBoundsException.class);



    }

}
