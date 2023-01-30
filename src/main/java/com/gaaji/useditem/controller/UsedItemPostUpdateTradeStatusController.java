package com.gaaji.useditem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gaaji.useditem.applicationservice.UsedItemPostUpdateTradeStatusService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UsedItemPostUpdateTradeStatusController {

	private final UsedItemPostUpdateTradeStatusService usedItemPostUpdateTradeStatusService;
}
