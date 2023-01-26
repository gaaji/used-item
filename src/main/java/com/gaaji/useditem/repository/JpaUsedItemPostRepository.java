package com.gaaji.useditem.repository;

import com.gaaji.useditem.controller.dto.PreviewPost;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUsedItemPostRepository extends JpaRepository<UsedItemPost, UsedItemPostId> {

	Long deleteBySellerIdAndPostId(SellerId sellerId, UsedItemPostId PostId);
//사진 url 고민해보기
	@Query("select new com.gaaji.useditem.controller.dto.PreviewPost(u.postId.id, u.representPictureUrl ,u.post.title, u.town.address, u.post.createdAt, u.price.price) from UsedItemPost u "
            + "where u.town.id =:townId ")
	List<PreviewPost> findByTownId(@Param("townId") String townId, PageRequest pageRequest);

}
