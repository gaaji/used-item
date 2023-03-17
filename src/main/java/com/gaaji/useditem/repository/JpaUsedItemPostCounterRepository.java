package com.gaaji.useditem.repository;

import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUsedItemPostCounterRepository extends JpaRepository<UsedItemPostCounter, UsedItemPostId> {

	@Query("select new com.gaaji.useditem.controller.dto.PreviewPostCount(uc.counter.interestCount, uc.counter.viewCount) from UsedItemPostCounter uc "
            + "where uc.usedItemPostId.id =:postId ")
	Optional<PreviewPostCount> findPreviewCountByPostId(@Param("postId") String postId);
}
