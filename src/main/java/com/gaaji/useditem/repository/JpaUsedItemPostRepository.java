package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUsedItemPostRepository extends JpaRepository<UsedItemPost, UsedItemPostId> {

	Long deleteBySellerIdAndPostId(SellerId sellerId, UsedItemPostId PostId);

}
