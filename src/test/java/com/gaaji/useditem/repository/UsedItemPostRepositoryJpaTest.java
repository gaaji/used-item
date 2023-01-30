package com.gaaji.useditem.repository;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.applicationservice.UsedItemPostUpdateService;
import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.netflix.discovery.converters.Auto;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UsedItemPostRepositoryJpaTest {

    @Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;

    @Autowired
    EntityManager em;
    
    
    @Test
    void 저장_조회하기 () throws Exception{
        //given
        UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
        );
        //when
        jpaUsedItemPostRepository.save(usedItemPost);
        UsedItemPost find = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo"))
                .get();

        //then
        assertThat(usedItemPost).isEqualTo(find);
    }

    /// 연관관계 생기는지 체크하기.
    
    @Test
    void 연관관계_생성으로_같이_Save되는지 () throws Exception{
        //given
        List<UsedItemPicture> pictureList = new ArrayList<>();
        pictureList.add(UsedItemPicture.of(UsedItemPictureId.of("foo"),"url"));

        UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
        );

        usedItemPost.addPictures(pictureList);
        jpaUsedItemPostRepository.save(usedItemPost);
        //when

        UsedItemPost finded = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo"))
                .orElseThrow();
        Field pictures = finded.getClass().getDeclaredField("pictures");
        pictures.setAccessible(true);
        //then


        //then
        assertThat( ((List<UsedItemPicture>)pictures.get(usedItemPost)).size()).isSameAs(1);


        
    
    }

    @Test
    void 사진의_Order로_정렬_테스트_사진이_존재할_때 () throws Exception{
        //given
        List<UsedItemPicture> pictureList = new ArrayList<>();
        pictureList.add(UsedItemPicture.of(UsedItemPictureId.of("foo1"),"url1"));
        pictureList.add(UsedItemPicture.of(UsedItemPictureId.of("foo"),"url"));


        UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
        );

        usedItemPost.addPictures(pictureList);
        jpaUsedItemPostRepository.save(usedItemPost);
        //when



        UsedItemPost finded = jpaUsedItemPostRepository.findPostByPostId(UsedItemPostId.of("foo"))
                .orElseThrow();



        List<String> urls = new ArrayList<>();
        urls.add("url");
        urls.add("url1");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);



        finded.modify(SellerId.of("bar"), dto);

        em.flush();
        em.clear();

        UsedItemPost last = jpaUsedItemPostRepository.findPostByPostId(
                        UsedItemPostId.of("foo"))
                .orElseThrow();

        //then
        Field fPictures = usedItemPost.getClass().getDeclaredField("pictures");
        fPictures.setAccessible(true);
        //when


        List<UsedItemPicture> modified = (List<UsedItemPicture>) fPictures.get(usedItemPost);
        //then



        assertThat(modified.get(0)).isEqualTo("url1");
        assertThat(modified.get(1)).isEqualTo("url");
        assertThat(last.getPicturesUrl().get(0)).isEqualTo("url");
        assertThat(last.getPicturesUrl().get(1)).isEqualTo("url1");


    }


}
