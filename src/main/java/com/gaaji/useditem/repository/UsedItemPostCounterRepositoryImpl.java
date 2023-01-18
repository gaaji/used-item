package com.gaaji.useditem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gaaji.useditem.domain.UsedItemPostId;

@RequiredArgsConstructor
@Repository
public class UsedItemPostCounterRepositoryImpl implements
        UsedItemPostCounterRepository {

    private final JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;

	@Override
	public void delete(UsedItemPostId Id) {
		jpaUsedItemPostCounterRepository.deleteById(Id);
	}
}
