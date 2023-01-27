package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.RESERVATION_STATUS_CHANGE_PRICE;

public class ReservationStatusChangePriceException extends AbstractApiException{

    public ReservationStatusChangePriceException() {
        super(RESERVATION_STATUS_CHANGE_PRICE);
    }
}
