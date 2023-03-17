package com.gaaji.useditem.repository;

import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUsedItemPostRepository extends JpaRepository<UsedItemPost, UsedItemPostId> {

	Long deleteBySellerIdAndPostId(SellerId sellerId, UsedItemPostId PostId);

	@Query("select new com.gaaji.useditem.controller.dto.PreviewPost(u.postId.id, u.representPictureUrl ,u.post.title, u.town.address, u.post.createdAt, u.price.price, u.tradeStatus, u.post.isHide) from UsedItemPost u "
            + "where u.town.id =:townId and u.post.createdAt <:createdAt")
	List<PreviewPost> findByTownId(@Param("townId") String townId, @Param("createdAt") LocalDateTime requestTime, PageRequest pageRequest);
	
	
	@Query("select new com.gaaji.useditem.controller.dto.PreviewPost(u.postId.id, u.representPictureUrl ,u.post.title, u.town.address, u.post.createdAt, u.price.price, u.tradeStatus, u.post.isHide) from UsedItemPost u "
            + "where u.sellerId.id =:authId order by u.post.createdAt desc")
	List<PreviewPost> findByauthId(@Param("authId") String authId);


	@Query("select distinct p from UsedItemPost p left join fetch UsedItemPicture up on up.post = p "
			+ "where p.postId = :postId")
	Optional<UsedItemPost> findPostByPostId(@Param("postId") UsedItemPostId usedItemPostId);
}
