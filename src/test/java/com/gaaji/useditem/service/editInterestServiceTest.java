package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.useditem.applicationservice.UsedItemDeleteService;
import com.gaaji.useditem.applicationservice.UsedItemPostInterestCountEditService;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@Transactional
@SpringBootTest
public class editInterestServiceTest {

	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
    @Autowired
    UsedItemPostInterestCountEditService usedItemPostInterestCountEditService;
    
    @Test
    void 조회수증가서비스 () throws Exception{
    	UsedItemPostCounter counter = UsedItemPostCounter.of(UsedItemPostId.of("foo"), Counter.of());
		// when
		this.jpaUsedItemPostCounterRepository.save(counter);
		
		for(int i =1; i<4; i++) {
			this.usedItemPostInterestCountEditService.editInterestCount("foo", 1);
		counter = this.jpaUsedItemPostCounterRepository.findById(UsedItemPostId.of("foo")).orElseThrow();
		 assertThat(counter.getInterestCount()).isEqualTo(i);
		}
        assertThatThrownBy(()->this.usedItemPostInterestCountEditService.editInterestCount("foo1",1)).isInstanceOf(NoSearchPostCounterException.class);

    }

}
