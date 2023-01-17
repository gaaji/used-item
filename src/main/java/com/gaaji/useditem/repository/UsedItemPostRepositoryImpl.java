package com.gaaji.useditem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPostId;

@RequiredArgsConstructor
@Repository
public class UsedItemPostRepositoryImpl implements
        UsedItemPostRepository {

    private final JpaUsedItemPostRepository jpaUsedItemPostRepository;

	@Override
	public Long delete(SellerId sellerId, UsedItemPostId usedItemPostId) {
		return jpaUsedItemPostRepository.deleteBySellerIdAndPostId(sellerId, usedItemPostId);
	}

}
