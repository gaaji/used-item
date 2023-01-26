package com.gaaji.useditem.repository;

import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gaaji.useditem.domain.UsedItemPostId;

@RequiredArgsConstructor
@Repository
public class UsedItemPostCounterRepositoryImpl implements
        UsedItemPostCounterRepository {

    private final JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;

    @Override
    public void save(UsedItemPostCounter usedItemPostCounter) {
        jpaUsedItemPostCounterRepository.save(usedItemPostCounter);

    }

    @Override
    public Optional<UsedItemPostCounter> findByPostId(UsedItemPostId postId) {
        return jpaUsedItemPostCounterRepository.findById(postId);
    }

	@Override
	public void delete(UsedItemPostId Id) {
		jpaUsedItemPostCounterRepository.deleteById(Id);
	}

	@Override
	public Optional<PreviewPostCount> findPreviewCountByPostId(String postId) {
		return jpaUsedItemPostCounterRepository.findPreviewCountByPostId(postId);
	}


}
