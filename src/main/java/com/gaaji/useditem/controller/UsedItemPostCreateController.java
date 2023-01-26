package com.gaaji.useditem.controller;

import com.gaaji.useditem.applicationservice.UsedItemPostCreateService;
import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.controller.dto.PostCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UsedItemPostCreateController {

    private final UsedItemPostCreateService usedItemPostCreateService;

    @PostMapping("/posts")
    public ResponseEntity<PostCreateResponse> createUsedItemPost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestHeader("X-TOWN-TOKEN") String townToken,
            @RequestBody PostCreateRequest dto
    ) {
        String postId = usedItemPostCreateService.createUsedItemPost(dto,  authorization, townToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PostCreateResponse(postId));
    }


}
