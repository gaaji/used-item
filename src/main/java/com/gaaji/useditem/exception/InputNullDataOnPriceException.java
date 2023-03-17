package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_PRICE;

public class InputNullDataOnPriceException extends AbstractApiException{

    public InputNullDataOnPriceException() {
        super(INPUT_NULL_DATA_ON_PRICE);
    }
}
