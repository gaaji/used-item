package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPostId;

public interface UsedItemPostRepository {

	Long delete(SellerId sellerId, UsedItemPostId usedItemPostId);

}
