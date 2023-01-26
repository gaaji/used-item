package com.gaaji.useditem.applicationservice;


import com.gaaji.useditem.adaptor.AuthServiceClient;
import com.gaaji.useditem.adaptor.RetrieveResponse;
import com.gaaji.useditem.applicationservice.UsedItemPostViewCountIncreaseService;
import com.gaaji.useditem.controller.dto.PostRetrieveResponse;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostRetrieveService {

    private final UsedItemPostRepository usedItemPostRepository;
    private final AuthServiceClient authServiceClient;
    private final UsedItemPostViewCountIncreaseService usedItemPostViewCountIncreaseService;


    public PostRetrieveResponse retrievePost(String postId, String authId) {

        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();
        UsedItemPostCounter counter = usedItemPostViewCountIncreaseService.retrievePost(
                UsedItemPostId.of(postId));

        RetrieveResponse sellerInfo = authServiceClient.retrieveAuth(usedItemPost.getSellerId());

        return PostRetrieveResponse.of(usedItemPost,counter,sellerInfo,authId);
    }



}
