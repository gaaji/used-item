package com.gaaji.useditem.repository;

import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.util.Optional;

import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

public interface UsedItemPostRepository {

    Optional<UsedItemPost> findByPostId(UsedItemPostId postId);

    default String nextId(){
        return UUID.randomUUID().toString();
    }

    void save(UsedItemPost usedItemPost);
	Long delete(SellerId sellerId, UsedItemPostId usedItemPostId);

	Optional<UsedItemPost> findById(UsedItemPostId postId);

	List<PreviewPost> findByTownId(String townId, PageRequest pageRequest);

	List<PreviewPost> findByauthId(String authId);




}
