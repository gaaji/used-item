package com.gaaji.useditem.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCon {



    @GetMapping("/")
    public void hello(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId){
       
    }
}
