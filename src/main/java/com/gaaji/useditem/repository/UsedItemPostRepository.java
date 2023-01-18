package com.gaaji.useditem.repository;

import java.util.Optional;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

public interface UsedItemPostRepository {

	Long delete(SellerId sellerId, UsedItemPostId usedItemPostId);

	Optional<UsedItemPost> findById(UsedItemPostId postId);


}
