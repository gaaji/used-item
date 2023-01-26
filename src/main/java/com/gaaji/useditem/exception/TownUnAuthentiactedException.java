package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.TOWN_UNAUTHENTICATED;

public class TownUnAuthentiactedException extends AbstractApiException{

    public TownUnAuthentiactedException() {
        super(TOWN_UNAUTHENTICATED);
    }
}
