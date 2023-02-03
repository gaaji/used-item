package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.NO_SEARCH_POST_COUNTER_EXCEPTION;;

public class NoSearchPostCounterException extends AbstractApiException {

	public NoSearchPostCounterException() {
		super(NO_SEARCH_POST_COUNTER_EXCEPTION);
	}

}
