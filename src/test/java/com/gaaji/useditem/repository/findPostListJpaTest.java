package com.gaaji.useditem.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.gaaji.useditem.controller.dto.PostListRetirveResponse;
import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import com.gaaji.useditem.repository.JpaUsedItemPostRepository;

@DataJpaTest
@Transactional
public class findPostListJpaTest {

	@Autowired
	JpaUsedItemPostRepository jpaUsedItemPostRepository;
	@Autowired
	JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;

	@Test
	void 조회리스트Jpa() throws Exception {
		// given

		UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of("foo"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"));

		// when
		jpaUsedItemPostRepository.save(usedItemPost);
		UsedItemPost find = jpaUsedItemPostRepository.findById(UsedItemPostId.of("foo")).get();
		UsedItemPostCounter counter = UsedItemPostCounter.of(UsedItemPostId.of("foo"), Counter.of());

		this.jpaUsedItemPostCounterRepository.save(counter);

		PageRequest pageRequest = PageRequest.of(0, 8, Sort.by("post.createdAt").descending());

		List<PreviewPost> previewPostList = this.jpaUsedItemPostRepository.findByTownId("townID", pageRequest);
		if (previewPostList.size() > 0) {

			PreviewPost post = previewPostList.get(0);
			PreviewPostCount count = this.jpaUsedItemPostCounterRepository.findPreviewCountByPostId(post.getPostId())
					.orElseThrow(() -> new RuntimeException());
			PostListRetirveResponse response = PostListRetirveResponse.of(post, count);
			assertThat(response.getPreviewPost().getPostId()).isEqualTo("foo");
			assertThat(response.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
			assertThat(response.getPreviewPost().getTitle()).isEqualTo("title");
			assertThat(response.getPreviewPost().getAddress()).isEqualTo("address");
			assertThat(response.getPreviewPost().getCreatedAt()).isNotNull();
			assertThat(response.getPreviewPost().getPrice()).isEqualTo(1000);
			assertThat(response.getPreviewPostCount().getInterestCount()).isEqualTo(0);
			assertThat(response.getPreviewPostCount().getViewCount()).isEqualTo(0);

		} else {
			System.err.print("찾지 못함");
		}

	}

	@Test
	void 내조회리스트Jpa() throws Exception {
		// given

		UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of("foo"), SellerId.of("bar"),
				Post.of("title", "contents", "category"), Price.of(1000L), true, null, Town.of("townID", "address"));

		// when
		jpaUsedItemPostRepository.save(usedItemPost);
		UsedItemPostCounter counter = UsedItemPostCounter.of(UsedItemPostId.of("foo"), Counter.of());
		this.jpaUsedItemPostCounterRepository.save(counter);

		List<PreviewPost> previewPostList = this.jpaUsedItemPostRepository.findByauthId("bar");
		if (previewPostList.size() > 0) {

			PreviewPost post = previewPostList.get(0);
			PreviewPostCount count = this.jpaUsedItemPostCounterRepository.findPreviewCountByPostId(post.getPostId())
					.orElseThrow(() -> new RuntimeException());
			PostListRetirveResponse response = PostListRetirveResponse.of(post, count);

			assertThat(response.getPreviewPost().getPostId()).isEqualTo("foo");
			assertThat(response.getPreviewPost().getRepresentPictureUrl()).isEqualTo(null);
			assertThat(response.getPreviewPost().getTitle()).isEqualTo("title");
			assertThat(response.getPreviewPost().getAddress()).isEqualTo("address");
			assertThat(response.getPreviewPost().getCreatedAt()).isNotNull();
			assertThat(response.getPreviewPost().getPrice()).isEqualTo(1000);
			assertThat(response.getPreviewPostCount().getInterestCount()).isEqualTo(0);
			assertThat(response.getPreviewPostCount().getViewCount()).isEqualTo(0);

		} else {
			System.err.print("찾지 못함");
		}

	}

}
