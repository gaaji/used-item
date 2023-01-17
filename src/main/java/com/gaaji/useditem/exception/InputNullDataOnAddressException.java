package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_ADDRESS;

public class InputNullDataOnAddressException extends AbstractApiException{

    public InputNullDataOnAddressException() {
        super(INPUT_NULL_DATA_ON_ADDRESS);
    }
}
