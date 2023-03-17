package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.NO_MATCH_AUTH_ID_AND_SELLER_ID;

public class NoMatchAuthIdAndSellerIdException extends AbstractApiException {

	public NoMatchAuthIdAndSellerIdException() {
		super(NO_MATCH_AUTH_ID_AND_SELLER_ID);
	}

}
