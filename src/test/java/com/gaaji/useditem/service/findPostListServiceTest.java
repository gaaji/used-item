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

	@Test
	void 조회리스트서비스() throws Exception {
		// given

		

		UsedItemPost usedItemPost1 = UsedItemPost.of(UsedItemPostId.of("foo1"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter1 = UsedItemPostCounter.of(UsedItemPostId.of("foo1"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost1);
		this.jpaUsedItemPostCounterRepository.save(counter1);

		UsedItemPost usedItemPost2 = UsedItemPost.of(UsedItemPostId.of("foo2"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter2 = UsedItemPostCounter.of(UsedItemPostId.of("foo2"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost2);
		this.jpaUsedItemPostCounterRepository.save(counter2);

		UsedItemPost usedItemPost3 = UsedItemPost.of(UsedItemPostId.of("foo3"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter3 = UsedItemPostCounter.of(UsedItemPostId.of("foo3"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost3);
		this.jpaUsedItemPostCounterRepository.save(counter3);

		UsedItemPost usedItemPost4 = UsedItemPost.of(UsedItemPostId.of("foo4"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter4 = UsedItemPostCounter.of(UsedItemPostId.of("foo4"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost4);
		this.jpaUsedItemPostCounterRepository.save(counter4);

		UsedItemPost usedItemPost5 = UsedItemPost.of(UsedItemPostId.of("foo5"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter5 = UsedItemPostCounter.of(UsedItemPostId.of("foo5"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost5);
		this.jpaUsedItemPostCounterRepository.save(counter5);

		UsedItemPost usedItemPost6 = UsedItemPost.of(UsedItemPostId.of("foo6"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter6 = UsedItemPostCounter.of(UsedItemPostId.of("foo6"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost6);
		this.jpaUsedItemPostCounterRepository.save(counter6);

		UsedItemPost usedItemPost7 = UsedItemPost.of(UsedItemPostId.of("foo7"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter7 = UsedItemPostCounter.of(UsedItemPostId.of("foo7"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost7);
		this.jpaUsedItemPostCounterRepository.save(counter7);

		UsedItemPost usedItemPost8 = UsedItemPost.of(UsedItemPostId.of("foo8"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter8 = UsedItemPostCounter.of(UsedItemPostId.of("foo8"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost8);
		this.jpaUsedItemPostCounterRepository.save(counter8);
		
		UsedItemPost usedItemPost9 = UsedItemPost.of(UsedItemPostId.of("foo9"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());

		UsedItemPostCounter counter9 = UsedItemPostCounter.of(UsedItemPostId.of("foo9"), Counter.of());
		// when
		jpaUsedItemPostRepository.save(usedItemPost9);
		this.jpaUsedItemPostCounterRepository.save(counter9);

		TownToken townToken = new TownToken();
		townToken.setAddress("address");
		townToken.setTownId("townID");
		 List<PostListRetirveResponse> list1 = usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 0);
		 List<PostListRetirveResponse> list2 = usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 1);
		 String foo = "foo";
		 int num =9;
		 String answer = "";
		 
		 for(PostListRetirveResponse response: list1) {
			 assertThat(response.getPreviewPost().getPostId()).isEqualTo(foo+num);
			 assertThat(response.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
			 assertThat(response.getPreviewPost().getTitle()).isEqualTo("title");
			 assertThat(response.getPreviewPost().getAddress()).isEqualTo("address");
			 assertThat(response.getPreviewPost().getCreatedAt()).isNotNull();
			 assertThat(response.getPreviewPost().getPrice()).isEqualTo(1000);
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
		 assertThat(response2.getPreviewPostCount().getInterestCount()).isEqualTo(0);
		 assertThat(response2.getPreviewPostCount().getViewCount()).isEqualTo(0);
		
	}
	
	@Test
	void 조회예외() throws Exception {
		UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of("foo"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"),
				Collections.emptyList());
		// when
		jpaUsedItemPostRepository.save(usedItemPost);

		TownToken townToken = new TownToken();
		townToken.setAddress("address");
		townToken.setTownId("townID");
		 assertThatThrownBy(()->usedItemPostListRetriveService.retriveUsedItemPostList( new ObjectMapper().writeValueAsString(townToken), 0)).isInstanceOf(NoSearchPostCounterException.class);
	}
	

}
