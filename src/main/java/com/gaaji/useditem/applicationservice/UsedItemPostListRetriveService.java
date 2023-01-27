package com.gaaji.useditem.applicationservice;

import java.util.List;

import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

public interface UsedItemPostListRetriveService {

	List<PostListRetirveResponse> retriveUsedItemPostList(String townToken, int pageNum);

	List<PostListRetirveResponse> retriveUsedItemMyPostList(String authId);


}
