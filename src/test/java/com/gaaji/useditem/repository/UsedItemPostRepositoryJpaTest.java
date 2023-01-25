package com.gaaji.useditem.repository;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UsedItemPostRepositoryJpaTest {

    @Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;
    
    
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



}
