package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.domain.TradeStatus;

public interface UsedItemPostUpdateTradeStatusService {

	void updateTradeStatus(String authId, String postId, String purchaserId, TradeStatus tradeStatus);

	void updateTradeStatusUnchangeable(String authId, String postId);

}
