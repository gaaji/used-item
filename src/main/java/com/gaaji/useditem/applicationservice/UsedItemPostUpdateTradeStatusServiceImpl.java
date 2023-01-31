package com.gaaji.useditem.applicationservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostUpdateTradeStatusServiceImpl implements UsedItemPostUpdateTradeStatusService {

	private final UsedItemPostRepository usedItemPostRepository;

	@Override
	public void updateTradeStatus(String authId, String postId, String purchaserId, TradeStatus tradeStatus) {
		UsedItemPost usedItemPost = this.usedItemPostRepository.findById(UsedItemPostId.of(postId)).orElseThrow(() -> new NoSearchPostException());

		if (usedItemPost.validateSellerId(authId)) {
			if (usedItemPost.getTradeStatus().equals(TradeStatus.UNCHANGEABLE)) {
				// TODO 에러 발생
			} else if (tradeStatus.equals(TradeStatus.SELLING)) {
				usedItemPost.updateTradeStatus(tradeStatus, null);
			} else {
				usedItemPost.updateTradeStatus(tradeStatus, purchaserId);
			}
		} else {
			throw new NoMatchAuthIdAndSellerIdException();
		}
	}

}
