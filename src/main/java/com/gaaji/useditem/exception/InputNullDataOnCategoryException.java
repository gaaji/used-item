package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INPUT_NULL_DATA_ON_CATEGORY;

public class InputNullDataOnCategoryException extends AbstractApiException{

    public InputNullDataOnCategoryException() {
        super(INPUT_NULL_DATA_ON_CATEGORY);
    }
}
