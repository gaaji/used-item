package com.gaaji.useditem.applicationservice;

import java.time.LocalDateTime;
import java.util.List;

import com.gaaji.useditem.controller.dto.PostListRetirveResponse;

public interface UsedItemPostListRetriveService {

	List<PostListRetirveResponse> retriveUsedItemPostList(String townToken, LocalDateTime requestTime, int pageNum);

	List<PostListRetirveResponse> retriveUsedItemMyPostList(String authId);


}
