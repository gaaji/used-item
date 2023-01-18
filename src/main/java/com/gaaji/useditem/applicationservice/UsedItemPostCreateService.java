package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.PurchaserId;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostCreateService {

    private final UsedItemPostRepository usedItemPostRepository;
    private final UsedItemPostCounterRepository usedItemPostCounterRepository;

    public String createUsedItemPost(PostCreateRequest dto) {

        UsedItemPost usedItemPost = UsedItemPost.of(UsedItemPostId.of(usedItemPostRepository.nextId())
                , SellerId.of(dto.getAuthId()),
                Post.of(dto.getTitle(), dto.getContents(), dto.getCategory())
                , Price.of(dto.getPrice()), dto.getCanSuggest(),
                WishPlace.of(dto.getPlaceX(), dto.getPlaceY(), dto.getPlaceText())
                , PurchaserId.of(null), Town.of(dto.getTownId(), dto.getAddress()),
                Collections.emptyList()
        );

        usedItemPostRepository.save(usedItemPost);
        usedItemPostCounterRepository.save(UsedItemPostCounter.of(
                UsedItemPostId.of(usedItemPost.getUsedItemPostId()),
                Counter.of()));

        return usedItemPost.getUsedItemPostId();
    }
}
