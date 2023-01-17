package com.gaaji.useditem.domain;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.repository.JpaUsedItemPostRepository;
import java.util.Collections;
import org.assertj.core.api.Assertions;
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
                ,true, null, null, Town.of("townID", "address")
                , Collections.emptyList()
        );

        //when
        jpaUsedItemPostRepository.save(usedItemPost);
        UsedItemPost find = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo"))
                .get();

        //then
        assertThat(usedItemPost).isEqualTo(find);
    
    }

}
