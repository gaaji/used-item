package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_POST_ID;

public class InputNullDataOnPostIdException extends AbstractApiException{

    public InputNullDataOnPostIdException() {
        super(INPUT_NULL_DATA_ON_POST_ID);
    }
}
