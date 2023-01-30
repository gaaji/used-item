package com.gaaji.useditem.applicationservice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemReverseHideServiceImpl implements UsedItemReverseHideService{

	private final UsedItemPostRepository usedItemPostRepository;

	@Override
	public void reverseHide(String authId, String postId) {
		UsedItemPost usedItemPost = this.usedItemPostRepository.findById(UsedItemPostId.of(postId)).orElseThrow(() -> new NoSearchPostException()); //TODO 여기도 예외 처리
		if(usedItemPost.validateSellerId(authId)) {
			usedItemPost.reverseHide();
		} else {
			throw new NoMatchAuthIdAndSellerIdException();
		}
	}
	
}
