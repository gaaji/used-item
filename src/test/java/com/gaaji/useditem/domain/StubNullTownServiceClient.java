package com.gaaji.useditem.domain;

import com.gaaji.useditem.adaptor.TownAddressResponse;
import com.gaaji.useditem.adaptor.TownServiceClient;

public class StubNullTownServiceClient implements TownServiceClient {

    @Override
    public TownAddressResponse retrieveTownAddress(String townId) {
        return TownAddressResponse.of("");
    }
}
