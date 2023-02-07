package com.gaaji.useditem.impl;

import com.gaaji.useditem.adaptor.InterestServiceClient;

public class StubInterestServiceClientReturnFalse implements InterestServiceClient {

    @Override
    public Boolean isExistInterest(String postId, String userId, String postType) {
        return false;
    }
}
