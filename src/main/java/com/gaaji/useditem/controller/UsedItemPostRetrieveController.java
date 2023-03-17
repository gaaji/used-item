package com.gaaji.useditem.controller;


import com.gaaji.useditem.applicationservice.UsedItemPostRetrieveService;
import com.gaaji.useditem.controller.dto.PostRetrieveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsedItemPostRetrieveController {

    private final UsedItemPostRetrieveService usedItemPostRetrieveService;


    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostRetrieveResponse> retrievePost(@PathVariable("postId") String postId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authId){
        return ResponseEntity.ok(usedItemPostRetrieveService.retrievePost(postId, authId));

    }

}
