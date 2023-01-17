package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUsedItemPostCounterRepository extends JpaRepository<UsedItemPostCounter, UsedItemPostId> {

}
