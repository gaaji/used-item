package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.CAN_NOT_UPDATE_TRADE_STATUS;

public class CanNotUpdateTradeStatusException extends AbstractApiException{

    public CanNotUpdateTradeStatusException() {
        super(CAN_NOT_UPDATE_TRADE_STATUS);
    }
}