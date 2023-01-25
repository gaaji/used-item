package com.gaaji.useditem.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PreviewPost {

	private String sellerId;
	//private String pictureUrl;
	private String title;
	private String address;
	private LocalDateTime createdAt;
	private Long price;
	
}
