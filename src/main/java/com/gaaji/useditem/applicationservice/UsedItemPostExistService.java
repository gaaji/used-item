package com.gaaji.useditem.applicationservice;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostExistService {
	
	private final UsedItemPostRepository usedItemPostRepository;


	public Boolean existUsedItemPost(String postId) {
		return this.usedItemPostRepository.existById(UsedItemPostId.of(postId));
	}

}
