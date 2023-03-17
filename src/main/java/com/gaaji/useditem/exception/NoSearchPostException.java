package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.NO_SEARCH_POST_EXCEPTION;


public class NoSearchPostException extends AbstractApiException{

	public NoSearchPostException() {
		super(NO_SEARCH_POST_EXCEPTION);
	}

}
