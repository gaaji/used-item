package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_TOWN_ID;

public class InputNullDataOnTownIdException extends AbstractApiException{

    public InputNullDataOnTownIdException() {
        super(INPUT_NULL_DATA_ON_TOWN_ID);
    }
}
