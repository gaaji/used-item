package com.gaaji.useditem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UsedItemPostCounterRepositoryImpl implements
        UsedItemPostCounterRepository {

    private final JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
}
