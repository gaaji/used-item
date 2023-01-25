package com.gaaji.useditem.applicationservice;

import java.util.List;

import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

public interface UsedItemPostListRetriveService {

	List<PostListRetirveResponse> retriveUsedItemPostList(String authId, String townToken, int pageNum);

}
