package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.applicationservice.UsedItemDeleteService;
import com.gaaji.useditem.applicationservice.UsedItemPostUpdateTradeStatusService;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.PurchaserId;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.CanNotUpdateTradeStatusException;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.exception.TradeStatusIsNotFinishException;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@Transactional
@SpringBootTest
public class UpdateTradeStatusServiceTest {

	@Autowired
    JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
    @Autowired
    UsedItemPostUpdateTradeStatusService usedItemPostUpdateTradeStatusService;
    
    @Test
    void 상태변경 () throws Exception{
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


        usedItemPostUpdateTradeStatusService.updateTradeStatus("bar", "foo", "aaa", TradeStatus.RESERVATION);
        assertThat(find.getTradeStatus()).isEqualTo(TradeStatus.RESERVATION);
        assertThat(find.getPurchaserId()).isEqualTo(PurchaserId.of("aaa"));
        
        usedItemPostUpdateTradeStatusService.updateTradeStatus("bar", "foo", "bbb", TradeStatus.SELLING);
        assertThat(find.getTradeStatus()).isEqualTo(TradeStatus.SELLING);
        assertThat(find.getPurchaserId()).isEqualTo(PurchaserId.of(null));
        
		assertThatThrownBy(()->usedItemPostUpdateTradeStatusService.updateTradeStatusUnchangeable("bar", "foo")).isInstanceOf(TradeStatusIsNotFinishException.class);

        
		usedItemPostUpdateTradeStatusService.updateTradeStatus("bar", "foo", "ccc", TradeStatus.FINISH);
        assertThat(find.getTradeStatus()).isEqualTo(TradeStatus.FINISH);
        assertThat(find.getPurchaserId()).isEqualTo(PurchaserId.of("ccc"));
		
        usedItemPostUpdateTradeStatusService.updateTradeStatusUnchangeable("bar", "foo");
        assertThat(find.getTradeStatus()).isEqualTo(TradeStatus.UNCHANGEABLE);
        assertThat(find.getPurchaserId()).isEqualTo(PurchaserId.of("ccc"));
        
		assertThatThrownBy(()->usedItemPostUpdateTradeStatusService.updateTradeStatus("bar", "foo", "aaa", TradeStatus.FINISH)).isInstanceOf(CanNotUpdateTradeStatusException.class);

        

    }
// 변경 불가는 판매 완료에서만 변경이 가능하다
  
}
