package com.gaaji.useditem.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemPostListRetriveService;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UsedItemPostListRetriveController {

	private final UsedItemPostListRetriveService usedItemPostListRetriveService;
    @GetMapping
    public ResponseEntity<List<PostListRetirveResponse>> createUsedItemPost(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, 
    		@RequestHeader("X-TOWN-TOKEN") String townToken , @RequestParam int pageNum ) {
    	log.info("pageNum = {} ", pageNum);
    	List<PostListRetirveResponse> posts = usedItemPostListRetriveService.retriveUsedItemPostList(townToken, pageNum);
        return ResponseEntity.status(HttpStatus.CREATED).body(posts);
    }
    
    @GetMapping("/my")
    public ResponseEntity<List<PostListRetirveResponse>> createUsedItemPost(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, 
    		@RequestHeader("X-TOWN-TOKEN") String townToken) {
    	List<PostListRetirveResponse> posts = usedItemPostListRetriveService.retriveUsedItemMyPostList(authId);
        return ResponseEntity.status(HttpStatus.CREATED).body(posts);
    }
}
