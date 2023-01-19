package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.useditem.applicationservice.UsedItemReverseHideService;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

@Transactional
@SpringBootTest
public class reverseHideServiceTest {

	@Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	UsedItemReverseHideService hideService;
	@Autowired
	UsedItemPostRepository usedItemPostRepository;
    
    
    @Test
    void 숨김 () throws Exception{
//        //given
//
    	UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
                , Collections.emptyList()
        );
//
//        //when
        jpaUsedItemPostRepository.save(usedItemPost);
//       
        this.hideService.reverseHide("bar", "foo");;

		UsedItemPost usedItemPost2 = this.usedItemPostRepository.findById(UsedItemPostId.of("foo")).orElseThrow(() -> new RuntimeException()); 
        System.out.println(usedItemPost2.getIsHide());
       
        
        this.hideService.reverseHide("bar", "foo");;
        usedItemPost2 = this.usedItemPostRepository.findById(UsedItemPostId.of("foo")).orElseThrow(() -> new RuntimeException()); 
        System.out.println(usedItemPost2.getIsHide());
    
    }
    
    @Test
    void 숨김예외1 () throws Exception{
//        //given
//
    	UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
                , Collections.emptyList()
        );
//
//        //when
        jpaUsedItemPostRepository.save(usedItemPost);
//       
        assertThatThrownBy(()->this.hideService.reverseHide("bar", "123")).isInstanceOf(NoSearchPostException.class);

		UsedItemPost usedItemPost2 = this.usedItemPostRepository.findById(UsedItemPostId.of("foo")).orElseThrow(() -> new RuntimeException()); 
        System.out.println(usedItemPost2.getIsHide());
       
        
  
    
    }
    
    @Test
    void 숨김예외2 () throws Exception{
//        //given
//
    	UsedItemPost usedItemPost = UsedItemPost.of(
                UsedItemPostId.of("foo"),
                SellerId.of("bar")
                , Post.of("title", "contents", "category"), Price.of(1000L)
                ,true, null,  Town.of("townID", "address")
                , Collections.emptyList()
        );
//
//        //when
        jpaUsedItemPostRepository.save(usedItemPost);
      
        assertThatThrownBy(()->this.hideService.reverseHide("123", "foo")).isInstanceOf(NoMatchAuthIdAndSellerIdException.class);

		UsedItemPost usedItemPost2 = this.usedItemPostRepository.findById(UsedItemPostId.of("foo")).orElseThrow(() -> new RuntimeException()); 
        System.out.println(usedItemPost2.getIsHide());
    
    
    }
}
