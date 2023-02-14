package com.gaaji.useditem.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Getter
public enum Constants {

    POST_TYPE("USEDITEM")



    ;


    private final String value;


}
