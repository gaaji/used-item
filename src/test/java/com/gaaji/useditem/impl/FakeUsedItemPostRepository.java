package com.gaaji.useditem.impl;

import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

public class FakeUsedItemPostRepository implements UsedItemPostRepository {

    private Map<UsedItemPostId, UsedItemPost> storage = new HashMap<>();

    @Override
    public Optional<UsedItemPost> findByPostId(UsedItemPostId postId) {
        return Optional.ofNullable(storage.get(postId));
    }

    @Override
    public void save(UsedItemPost usedItemPost) {
        storage.put(UsedItemPostId.of(usedItemPost.getUsedItemPostId()), usedItemPost);
    }

    @Override
    public Long delete(SellerId sellerId, UsedItemPostId usedItemPostId) {
        return null;
    }

    @Override
    public Optional<UsedItemPost> findById(UsedItemPostId postId) {
        return Optional.empty();
    }

	@Override
	public List<PreviewPost> findByTownId(String townId, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
