package com.gaaji.useditem.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping("/auth/{authId}")
     RetrieveResponse retrieveAuth(@PathVariable("authId") String authId);
}
