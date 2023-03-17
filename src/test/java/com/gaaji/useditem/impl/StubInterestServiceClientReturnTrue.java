package com.gaaji.useditem.impl;

import com.gaaji.useditem.adaptor.InterestServiceClient;

public class StubInterestServiceClientReturnTrue implements InterestServiceClient {

    @Override
    public Boolean isExistInterest(String postId, String userId, String postType) {
        return true;
    }
}
