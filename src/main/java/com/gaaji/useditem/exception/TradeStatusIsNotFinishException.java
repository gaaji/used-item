package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.Trade_STATUS_IS_NOT_FINISH;

public class TradeStatusIsNotFinishException extends AbstractApiException{

    public TradeStatusIsNotFinishException() {
        super(Trade_STATUS_IS_NOT_FINISH);
    }
}
