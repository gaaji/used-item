package com.gaaji.useditem.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemPostListRetriveService;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class UsedItemPostListRetriveController {

	private final UsedItemPostListRetriveService usedItemPostListRetriveService;
    @GetMapping("?pageNum")
    public ResponseEntity<List<PostListRetirveResponse>> createUsedItemPost(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, 
    		@RequestHeader("X-TOWN-TOKEN") String townToken , @RequestBody int pageNum ) {
    	List<PostListRetirveResponse> posts = usedItemPostListRetriveService.retriveUsedItemPostList(authId, townToken, pageNum);
        return ResponseEntity.status(HttpStatus.CREATED).body(posts);
    }
}