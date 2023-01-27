package com.gaaji.useditem.applicationservice;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;
import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.controller.dto.PreviewPostCount;
import com.gaaji.useditem.controller.dto.TownToken;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.NoSearchPostCounterException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPostListRetriveServiceImpl implements UsedItemPostListRetriveService{

	private final UsedItemPostRepository usedItemPostRepository;
	private final UsedItemPostCounterRepository usedItemPostCounterRepository;
	
	@Override
	public List<PostListRetirveResponse> retriveUsedItemPostList(String townHeader, int pageNum) {
		
		TownToken townToken = null;
        try {
             townToken = new ObjectMapper().readValue(townHeader, TownToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        PageRequest pageRequest = PageRequest.of(pageNum, 8, Sort.by("post.createdAt").descending());
        
        townToken.getTownId();
        
        List<PreviewPost> previewPostList = this.usedItemPostRepository.findByTownId(townToken.getTownId(),pageRequest);
        if(previewPostList.size() > 0) {
        	return getPostListRetirveResponse(previewPostList);
        } else {
        	return null;
        }
		
	}

	private List<PostListRetirveResponse> getPostListRetirveResponse(List<PreviewPost> previewPostList) {
		List<PostListRetirveResponse> postListRetirveResponseList = new ArrayList<PostListRetirveResponse>();
		for(PreviewPost previewPost : previewPostList) {
			PreviewPostCount previewPostCount = this.usedItemPostCounterRepository.findPreviewCountByPostId(previewPost.getPostId()).orElseThrow(() -> new NoSearchPostCounterException());
			PostListRetirveResponse postListRetirveResponse = PostListRetirveResponse.of(previewPost, previewPostCount);
			postListRetirveResponseList.add(postListRetirveResponse);
		}

		return postListRetirveResponseList;
	}

	@Override
	public List<PostListRetirveResponse> retriveUsedItemMyPostList(String authId) {
                        
        List<PreviewPost> previewPostList = this.usedItemPostRepository.findByauthId(authId);
        if(previewPostList.size() > 0) {
        	return getPostListRetirveResponse(previewPostList);
        } else {
        	return null;
        }
	}


}
