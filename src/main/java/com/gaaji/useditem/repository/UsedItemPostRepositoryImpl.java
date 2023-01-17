package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UsedItemPostRepositoryImpl implements
        UsedItemPostRepository {

    private final JpaUsedItemPostRepository jpaUsedItemPostRepository;

    @Override
    public Optional<UsedItemPost> findByPostId(UsedItemPostId postId) {
        return jpaUsedItemPostRepository.findById(postId);
    }

    @Override
    public void save(UsedItemPost usedItemPost) {
        jpaUsedItemPostRepository.save(usedItemPost);
    }
}
