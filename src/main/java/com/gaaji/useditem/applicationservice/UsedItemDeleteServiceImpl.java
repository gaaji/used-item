package com.gaaji.useditem.applicationservice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemDeleteServiceImpl implements UsedItemDeleteService {

	private final UsedItemPostRepository usedItemPostRepository;
	private final UsedItemPostCounterRepository usedItemPostCounterRepository;
	
	@Override
	public void deleteUsedItem(String authId, String postId) {
		if(usedItemPostRepository.delete(SellerId.of(authId), UsedItemPostId.of(postId))==1) {
			usedItemPostCounterRepository.delete(UsedItemPostId.of(postId));
		} else {
			//TODO 에러 발생
			throw new RuntimeException();
		}
		
	}

}
