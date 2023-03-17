package com.gaaji.useditem.repository;

import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;

import com.gaaji.useditem.domain.UsedItemPostId;

import com.gaaji.useditem.domain.UsedItemPostId;

public interface UsedItemPostCounterRepository {

    void save(UsedItemPostCounter usedItemPostCounter);

    Optional<UsedItemPostCounter> findByPostId(UsedItemPostId postId);
	void delete(UsedItemPostId id);

	Optional<PreviewPostCount> findPreviewCountByPostId(String postId);



}
