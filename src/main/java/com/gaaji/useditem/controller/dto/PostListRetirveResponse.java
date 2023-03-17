package com.gaaji.useditem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostListRetirveResponse {
	
	private PreviewPost previewPost;
	private PreviewPostCount previewPostCount;

	public static PostListRetirveResponse of(PreviewPost previewPost, PreviewPostCount previewPostCount){
        return new PostListRetirveResponse(previewPost,  previewPostCount);
    }
	
}
