package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.domain.SellerId;

@RequiredArgsConstructor
@Repository
public class UsedItemPostRepositoryImpl implements
        UsedItemPostRepository {

    private final JpaUsedItemPostRepository jpaUsedItemPostRepository;

    @Override
    public Optional<UsedItemPost> findByPostId(UsedItemPostId postId) {
        return jpaUsedItemPostRepository.findPostByPostId(postId);
    }

    @Override
    public void save(UsedItemPost usedItemPost) {
        jpaUsedItemPostRepository.save(usedItemPost);
    }
	@Override
	public Long delete(SellerId sellerId, UsedItemPostId usedItemPostId) {
		return jpaUsedItemPostRepository.deleteBySellerIdAndPostId(sellerId, usedItemPostId);
	}

	@Override
	public Optional<UsedItemPost> findById(UsedItemPostId postId) {
		return jpaUsedItemPostRepository.findById(postId);
	}

	@Override
	public List<PreviewPost> findByTownId(String townId, PageRequest pageRequest, LocalDateTime requestTime) {
		return jpaUsedItemPostRepository.findByTownId(townId, requestTime, pageRequest);
	}

	@Override
	public List<PreviewPost> findByauthId(String authId) {
		return jpaUsedItemPostRepository.findByauthId(authId);
	}

	@Override
	public Boolean existById(UsedItemPostId postId) {
		return jpaUsedItemPostRepository.existsById(postId);
	}



}
