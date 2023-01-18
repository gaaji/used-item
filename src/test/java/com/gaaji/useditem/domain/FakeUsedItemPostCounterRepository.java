package com.gaaji.useditem.domain;

import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUsedItemPostCounterRepository implements UsedItemPostCounterRepository {

    private Map<UsedItemPostId, UsedItemPostCounter> storage = new HashMap<>();

    @Override
    public void save(UsedItemPostCounter usedItemPostCounter) {
        storage.put(UsedItemPostId.of(usedItemPostCounter.getUsedItemPostId()), usedItemPostCounter);
    }

    @Override
    public Optional<UsedItemPostCounter> findByPostId(UsedItemPostId postId) {
        return Optional.ofNullable(storage.get(postId));
    }
}
