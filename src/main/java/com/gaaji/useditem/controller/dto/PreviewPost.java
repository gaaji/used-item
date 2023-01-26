package com.gaaji.useditem.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PreviewPost {

	private String postId;
	private String representPictureUrl;
	private String title;
	private String address;
	private LocalDateTime createdAt;
	private Long price;
	
}
