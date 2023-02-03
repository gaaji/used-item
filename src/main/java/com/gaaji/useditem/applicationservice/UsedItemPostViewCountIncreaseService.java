package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsedItemPostViewCountIncreaseService {

    private final UsedItemPostCounterRepository usedItemPostCounterRepository;

    public UsedItemPostCounter retrievePost(UsedItemPostId postId) {
        UsedItemPostCounter counter = usedItemPostCounterRepository.findByPostId(postId)
                .orElseThrow(NoSearchPostCounterException::new);
        counter.increaseViewCount();
        return counter;
    }
}
