package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUsedItemPostRepository extends JpaRepository<UsedItemPost, UsedItemPostId> {

	Long deleteBySellerIdAndUsedItemPostId(SellerId sellerId, UsedItemPostId usedItemPostId);
	
}
