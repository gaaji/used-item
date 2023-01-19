package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.No_Search_Post_Exception;

public class NoSearchPostException extends AbstractApiException{

	public NoSearchPostException() {
		super(No_Search_Post_Exception);
	}

}
