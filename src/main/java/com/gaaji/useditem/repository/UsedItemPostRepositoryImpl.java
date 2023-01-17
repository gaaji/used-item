package com.gaaji.useditem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UsedItemPostRepositoryImpl implements
        UsedItemPostRepository {

    private final JpaUsedItemPostRepository jpaUsedItemPostRepository;

}
