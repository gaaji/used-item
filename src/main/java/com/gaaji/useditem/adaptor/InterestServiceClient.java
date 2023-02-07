package com.gaaji.useditem.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("interest-service")
public interface InterestServiceClient {

    @GetMapping("/interest/post/{postId}/user/{userId}")
    Boolean isExistInterest(@PathVariable("postId") String postId,
            @PathVariable("userId") String userId, @RequestParam(name = "type") String postType);
}
