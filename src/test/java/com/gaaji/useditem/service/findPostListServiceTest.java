package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.applicationservice.UsedItemPostListRetriveService;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;
import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.controller.dto.TownToken;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@Transactional
@SpringBootTest
public class findPostListServiceTest {

	@Autowired
	UsedItemPostListRetriveService usedItemPostListRetriveService;
	@Autowired
	JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;

	@BeforeEach
	void beforeEach(){
		jpaUsedItemPostRepository.deleteAll();;
		jpaUsedItemPostCounterRepository.deleteAll();;
	}

	@Test
	void 조회리스트서비스() throws Exception {
		// given
		 String foo = "foo";
		 int num =9;
		
		 for(int i = 1; i<10;i++) {
			 UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of(foo+i), SellerId.of("bar"),
						Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"));

				UsedItemPostCounter counter = UsedItemPostCounter.of(UsedItemPostId.of(foo+i), Counter.of());
				// when
				jpaUsedItemPostRepository.save(usedItemPost);
				this.jpaUsedItemPostCounterRepository.save(counter);

		 }


		TownToken townToken = new TownToken("townID", true);
	
		 List<PostListRetirveResponse> list1 = usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 0);
		 List<PostListRetirveResponse> list2 = usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 1);
		
		 
		 
		 for(PostListRetirveResponse response: list1) {
			 assertThat(response.getPreviewPost().getPostId()).isEqualTo(foo+num);
			 assertThat(response.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
			 assertThat(response.getPreviewPost().getTitle()).isEqualTo("title");
			 assertThat(response.getPreviewPost().getAddress()).isEqualTo("address");
			 assertThat(response.getPreviewPost().getCreatedAt()).isNotNull();
			 assertThat(response.getPreviewPost().getPrice()).isEqualTo(1000);
			 assertThat(response.getPreviewPost().getTradeStatus()).isEqualTo(TradeStatus.SELLING);
			 assertThat(response.getPreviewPost().isHide()).isEqualTo(false);
			 assertThat(response.getPreviewPostCount().getInterestCount()).isEqualTo(0);
			 assertThat(response.getPreviewPostCount().getViewCount()).isEqualTo(0);
			 num--;
		 }
		 
		 PostListRetirveResponse response2 = list2.get(0);
		 assertThat(response2.getPreviewPost().getPostId()).isEqualTo("foo1");
		 assertThat(response2.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
		 assertThat(response2.getPreviewPost().getTitle()).isEqualTo("title");
		 assertThat(response2.getPreviewPost().getAddress()).isEqualTo("address");
		 assertThat(response2.getPreviewPost().getCreatedAt()).isNotNull();
		 assertThat(response2.getPreviewPost().getPrice()).isEqualTo(1000);
		 assertThat(response2.getPreviewPost().getTradeStatus()).isEqualTo(TradeStatus.SELLING);
		 assertThat(response2.getPreviewPost().isHide()).isEqualTo(false);
		 assertThat(response2.getPreviewPostCount().getInterestCount()).isEqualTo(0);
		 assertThat(response2.getPreviewPostCount().getViewCount()).isEqualTo(0);
		
	}
	
	@Test
	void 조회예외() throws Exception {
		UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of("foo"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"));
		// when
		jpaUsedItemPostRepository.save(usedItemPost);

		TownToken townToken = new TownToken("townID", true);

		 assertThatThrownBy(()->usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 0)).isInstanceOf(NoSearchPostCounterException.class);
		 assertThatThrownBy(()->usedItemPostListRetriveService.retriveUsedItemMyPostList("bar")).isInstanceOf(NoSearchPostCounterException.class);

	}
	
	@Test
	void 내조회리스트서비스() throws Exception {
		// given
		 String foo = "foo";
		 int num =9;
		
		 for(int i = 1; i<10;i++) {
			 UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of(foo+i), SellerId.of("bar"),
						Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"));

				UsedItemPostCounter counter = UsedItemPostCounter.of(UsedItemPostId.of(foo+i), Counter.of());
				// when
				jpaUsedItemPostRepository.save(usedItemPost);
				this.jpaUsedItemPostCounterRepository.save(counter);

		 }


		TownToken townToken = new TownToken("townID", true);
	
		 List<PostListRetirveResponse> list = usedItemPostListRetriveService.retriveUsedItemMyPostList("bar");
		 
		 
		 for(PostListRetirveResponse response: list) {
			 assertThat(response.getPreviewPost().getPostId()).isEqualTo(foo+num);
			 assertThat(response.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
			 assertThat(response.getPreviewPost().getTitle()).isEqualTo("title");
			 assertThat(response.getPreviewPost().getAddress()).isEqualTo("address");
			 assertThat(response.getPreviewPost().getCreatedAt()).isNotNull();
			 assertThat(response.getPreviewPost().getPrice()).isEqualTo(1000);
			 assertThat(response.getPreviewPost().getTradeStatus()).isEqualTo(TradeStatus.SELLING);
			 assertThat(response.getPreviewPost().isHide()).isEqualTo(false);
			 assertThat(response.getPreviewPostCount().getInterestCount()).isEqualTo(0);
			 assertThat(response.getPreviewPostCount().getViewCount()).isEqualTo(0);
			 num--;
		 }
		 
		
	}

}
