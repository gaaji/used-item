package com.gaaji.useditem.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemDeleteService;
import com.gaaji.useditem.applicationservice.UsedItemReverseHideService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class UsedItemReverseHideController {

		private final UsedItemReverseHideService usedItemReverseHideService;
		
		@PatchMapping("/my/{postId}")
	    public ResponseEntity<Void> deleteTown(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String postId){
			usedItemReverseHideService.reverseHide(authId, postId);
	        return ResponseEntity.status(HttpStatus.OK).build();
	    }
}
