package com.gaaji.useditem.applicationservice;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemReverseHideServiceImpl implements UsedItemReverseHideService{

	private final UsedItemPostRepository usedItemPostRepository;

	@Override
	public void reverseHide(String authId, String postId) {
		UsedItemPost usedItemPost = this.usedItemPostRepository.findById(UsedItemPostId.of(postId)).orElseThrow(() -> new RuntimeException()); //TODO 여기도 예외 처리
		if(usedItemPost.checkSellerId(authId)) {
			usedItemPost.reverseHide();
		} else {
			//TODO 틀리면 에러 발생
		}
	}
	
}
