package com.gaaji.useditem.impl;

import com.gaaji.useditem.adaptor.TownAddressResponse;
import com.gaaji.useditem.adaptor.TownServiceClient;

public class StubTownServiceClient implements TownServiceClient {

    @Override
    public TownAddressResponse retrieveTownAddress(String townId) {
        return TownAddressResponse.of("foo");
    }
}
