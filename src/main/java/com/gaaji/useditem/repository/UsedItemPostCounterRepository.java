package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;

public interface UsedItemPostCounterRepository {

    void save(UsedItemPostCounter usedItemPostCounter);

    Optional<UsedItemPostCounter> findByPostId(UsedItemPostId postId);
}
