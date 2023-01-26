package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.No_Search_Post_Counter_Exception;;

public class NoSearchPostCounterException extends AbstractApiException {

	public NoSearchPostCounterException() {
		super(No_Search_Post_Counter_Exception);
	}

}
