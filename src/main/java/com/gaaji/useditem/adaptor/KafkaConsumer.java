package com.gaaji.useditem.adaptor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.gaaji.useditem.applicationservice.UsedItemPicturesUploadService;
import com.gaaji.useditem.applicationservice.UsedItemPostInterestCountEditService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {
	
	private final UsedItemPostInterestCountEditService UsedItemPostInterestCountEditService;
	
	@KafkaListener(topics = "used-item-increase-interest-count", groupId = "used-item")
	 public void interestCountListen(String postId) {
       this.UsedItemPostInterestCountEditService.editInterestCount(postId, 1);
    }

	@KafkaListener(topics = "used-item-decrease-interest-count", groupId = "used-item")
	 public void interestCountDecreaseListen(String postId) {
      this.UsedItemPostInterestCountEditService.editInterestCount(postId, -1);
   }
}
