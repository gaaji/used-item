package com.gaaji.useditem.controller;

import com.gaaji.useditem.applicationservice.UsedItemPostUpdateService;
import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsedItemPostUpdateController {

    private final UsedItemPostUpdateService usedItemPostUpdateService;

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") String postId,
    @RequestBody PostUpdateRequest dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        usedItemPostUpdateService.updatePost(postId,authorization, dto);

        return ResponseEntity.ok().build();
    }
}
