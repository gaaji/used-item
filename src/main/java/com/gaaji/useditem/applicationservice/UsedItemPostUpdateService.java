package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostUpdateService {

    private final UsedItemPostRepository usedItemPostRepository;

    public void updatePost(String postId, String authorization, PostUpdateRequest dto) {
        UsedItemPost post = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();
        post.modify(SellerId.of(authorization), dto);

    }
}
