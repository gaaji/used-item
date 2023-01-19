package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.useditem.applicationservice.UsedItemDeleteService;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@Transactional
@SpringBootTest
public class deleteServiceTest {

	@Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
    @Autowired
    UsedItemDeleteService usedItemDeleteService;
    
    @Test
    void 삭제서비스 () throws Exception{
        //given

    	UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null, Town.of("townID", "address")
        );

        //when
        jpaUsedItemPostRepository.save(usedItemPost);
        UsedItemPost find = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo")).get();

        UsedItemPostCounter counter = UsedItemPostCounter.of( UsedItemPostId.of("foo"), Counter.of());




        this.jpaUsedItemPostCounterRepository.save(counter);


        usedItemDeleteService.deleteUsedItem("bar", "foo");
        System.out.println("완료");


    }

    @Test
    void  삭제예외() throws Exception{
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

        UsedItemPostCounter counter = UsedItemPostCounter.of( UsedItemPostId.of("foo"), Counter.of());
        
        
        
        
        this.jpaUsedItemPostCounterRepository.save(counter);
       
        assertThatThrownBy(()->usedItemDeleteService.deleteUsedItem("bar", "123")).isInstanceOf(NoSearchPostException.class);

        System.out.println("완료");
       
    
    }
}
