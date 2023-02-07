package com.gaaji.useditem.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemPostExistService;
import com.gaaji.useditem.applicationservice.UsedItemPostListRetriveService;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class UsedItemPostExistController {

	private final UsedItemPostExistService usedItemPostExistService;
	
    @GetMapping("/exist/{postId}")
    public ResponseEntity<Boolean> existUsedItemPost(@PathVariable("postId") String postId) {
    	
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usedItemPostExistService.existUsedItemPost(postId));
    }
}
