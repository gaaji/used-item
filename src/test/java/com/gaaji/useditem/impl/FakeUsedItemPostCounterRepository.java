package com.gaaji.useditem.impl;

import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUsedItemPostCounterRepository implements UsedItemPostCounterRepository {

    private Map<UsedItemPostId, UsedItemPostCounter> storage = new HashMap<>();

    @Override
    public void save(UsedItemPostCounter usedItemPostCounter) {
        storage.put(UsedItemPostId.of(usedItemPostCounter.getUsedItemPostIdToString()), usedItemPostCounter);
    }

    @Override
    public Optional<UsedItemPostCounter> findByPostId(UsedItemPostId postId) {
        return Optional.ofNullable(storage.get(postId));
    }

    @Override
    public void delete(UsedItemPostId id) {

    }
}
