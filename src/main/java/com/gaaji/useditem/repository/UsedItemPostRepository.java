package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;
import java.util.UUID;

public interface UsedItemPostRepository {

    Optional<UsedItemPost> findByPostId(UsedItemPostId postId);

    default String nextId(){
        return UUID.randomUUID().toString();
    }

    void save(UsedItemPost usedItemPost);
}
