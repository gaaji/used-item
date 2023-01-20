package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.JSON_PARSING_ERROR;

public class JsonParsingException extends AbstractApiException{

    public JsonParsingException() {
        super(JSON_PARSING_ERROR);
    }
}
