package com.gaaji.useditem.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@DataJpaTest
@Transactional
public class deleteJpaTest {

	@Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
    
    
    @Test
    void 삭제Jpa () throws Exception{
        //given

    	UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
                , Collections.emptyList()
        );

        //when
        jpaUsedItemPostRepository.save(usedItemPost);
        UsedItemPost find = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo"))
                .get();

        UsedItemPostCounter counter = UsedItemPostCounter.of( UsedItemPostId.of("foo"), Counter.of());
        
        Long a =jpaUsedItemPostRepository.deleteBySellerIdAndPostId( SellerId.of("bar"), UsedItemPostId.of("foo"));
        System.out.println(a);
        
        
        this.jpaUsedItemPostCounterRepository.save(counter);
        
        this.jpaUsedItemPostCounterRepository.findById(UsedItemPostId.of("foo"));
        jpaUsedItemPostCounterRepository.deleteById(UsedItemPostId.of("foo"));
        System.out.println("완료");
       
    
    }
}
