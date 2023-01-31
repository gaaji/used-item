package com.gaaji.useditem.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemPostUpdateTradeStatusService;
import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.domain.TradeStatus;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts/TradeStatus")
@RequiredArgsConstructor
@RestController
public class UsedItemPostUpdateTradeStatusController {

	private final UsedItemPostUpdateTradeStatusService usedItemPostUpdateTradeStatusService;
	
	@PatchMapping("/{postId}")
    public ResponseEntity<Void> updateTradeStatus(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String postId, 
    		@RequestBody String purchaserId, @RequestBody TradeStatus tradeStatus){
		usedItemPostUpdateTradeStatusService.updateTradeStatus(authId, postId, purchaserId, tradeStatus);
        return ResponseEntity.ok().build();
    }
}
