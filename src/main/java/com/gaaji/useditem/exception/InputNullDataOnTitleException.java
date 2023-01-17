package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_TITLE;

public class InputNullDataOnTitleException extends AbstractApiException {

    public InputNullDataOnTitleException() {
        super(INPUT_NULL_DATA_ON_TITLE);
    }
}
