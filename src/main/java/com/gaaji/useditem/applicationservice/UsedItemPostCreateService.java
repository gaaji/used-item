package com.gaaji.useditem.applicationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.adaptor.TownServiceClient;
import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.controller.dto.TownToken;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import com.gaaji.useditem.exception.JsonParsingException;
import com.gaaji.useditem.exception.TownUnAuthentiactedException;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostCreateService {

    private final UsedItemPostRepository usedItemPostRepository;
    private final UsedItemPostCounterRepository usedItemPostCounterRepository;
    private final TownServiceClient townServiceClient;

    public String createUsedItemPost(PostCreateRequest dto, String authorization, String townToken) {
        try {
            return saveEntity(createUsedItemPostEntity(dto, authorization, parseTownToken(townToken)));
        } catch (JsonProcessingException e) {
            throw new JsonParsingException();
        }
    }

    private String saveEntity(UsedItemPost usedItemPost) {
        usedItemPostRepository.save(usedItemPost);
        usedItemPostCounterRepository.save(UsedItemPostCounter.of(
                UsedItemPostId.of(usedItemPost.getUsedItemPostId()),
                Counter.of()));
        return usedItemPost.getUsedItemPostId();
    }

    private TownToken parseTownToken(String townToken) throws JsonProcessingException {
        TownToken token = new ObjectMapper().readValue(townToken, TownToken.class);
        if(!token.isAuthenticated())
            throw new TownUnAuthentiactedException();
        return token;
    }

    private UsedItemPost createUsedItemPostEntity(PostCreateRequest dto, String authorization,
            TownToken token) {
        // TODO refactor factory method
        return UsedItemPost.of(UsedItemPostId.of(usedItemPostRepository.nextId())
                , SellerId.of(authorization),
                Post.of(dto.getTitle(), dto.getContents(), dto.getCategory())
                , Price.of(dto.getPrice()), dto.getCanSuggest(),
                WishPlace.of(dto.getPlaceX(), dto.getPlaceY(), dto.getPlaceText())
                ,  Town.of(token.getTownId(),  getAddressFromTownService(token))
        );
    }

    private String getAddressFromTownService(TownToken token) {
        return townServiceClient.retrieveTownAddress(
                token.getTownId()).getAddress();
    }
}
