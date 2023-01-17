package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_SELLER_ID;

public class InputNullDataOnSellerIdException extends AbstractApiException{

    public InputNullDataOnSellerIdException() {
        super(INPUT_NULL_DATA_ON_SELLER_ID);
    }
}
