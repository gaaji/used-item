package com.gaaji.useditem.applicationservice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostInterestCountEditService {
	
	private final UsedItemPostCounterRepository usedItemPostCounterRepository;

	public void editInterestCount(String postId, int i) {
		UsedItemPostCounter usedItemPostCounter = usedItemPostCounterRepository.findByPostId(UsedItemPostId.of(postId)).orElseThrow(NoSearchPostCounterException::new);
		usedItemPostCounter.editInterestCount(i);
		
	}

}
