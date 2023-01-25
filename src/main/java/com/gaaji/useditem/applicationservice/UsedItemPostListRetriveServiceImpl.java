package com.gaaji.useditem.applicationservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.controller.dto.PostListRetirveResponse;
import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.controller.dto.TownToken;
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
	public List<PostListRetirveResponse> retriveUsedItemPostList(String authId, String townHeader, int pageNum) {
		
		TownToken townToken = null;
        try {
             townToken = new ObjectMapper().readValue(townHeader, TownToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        PageRequest pageRequest = PageRequest.of(pageNum, 8, Sort.by("createdAt").descending());
        
        townToken.getTownId();
        
        List<PreviewPost> previewPost = this.usedItemPostRepository.findByTownId(townToken.getTownId(),pageRequest);
        
		return null;
	}

}
