package com.gaaji.useditem.impl;

import com.gaaji.useditem.adaptor.AuthServiceClient;
import com.gaaji.useditem.adaptor.RetrieveResponse;

public class StubAuthServiceClient implements AuthServiceClient {

    @Override
    public RetrieveResponse retrieveAuth(String authId) {
       return new RetrieveResponse("foo", "익명", 36.5);
    }
}
