package com.gaaji.useditem.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("town-service")
public interface TownServiceClient {

    @GetMapping("/town/{townId}")
    TownAddressResponse retrieveTownAddress(@PathVariable("townId") String townId);
}
