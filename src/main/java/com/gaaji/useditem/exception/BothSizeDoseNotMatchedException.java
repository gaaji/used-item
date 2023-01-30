package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.BOTH_SIZE_DOSE_NOT_MATCHED;

public class BothSizeDoseNotMatchedException extends AbstractApiException{

    public BothSizeDoseNotMatchedException() {
        super(BOTH_SIZE_DOSE_NOT_MATCHED);
    }
}
