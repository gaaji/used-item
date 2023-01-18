package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

@RequiredArgsConstructor
@Repository
public class UsedItemPostRepositoryImpl implements
        UsedItemPostRepository {

    private final JpaUsedItemPostRepository jpaUsedItemPostRepository;

    @Override
    public Optional<UsedItemPost> findByPostId(UsedItemPostId postId) {
        return jpaUsedItemPostRepository.findById(postId);
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



}
