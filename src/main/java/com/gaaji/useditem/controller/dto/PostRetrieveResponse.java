package com.gaaji.useditem.controller.dto;

import com.gaaji.useditem.adaptor.RetrieveResponse;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class PostRetrieveResponse {

    private String postId;
    private int suggestCount;
    private int interestCount;
    private int viewCount;
    private int chatCount;
    private String title;
    private String contents;
    private String category;
    private LocalDateTime createdAt;
    private Boolean isHide;
    private long price;
    private Boolean canSuggest;
    private String wishX;
    private String wishY;
    private String wishText;
    private String townId;
    private String townAddress;
    private String sellerId;
    private String sellerNickname;
    private double sellerMannerTemperature;
    private Boolean isMine;
    private TradeStatus tradeStatus;

    private List<String> picturesUrl;

    private PostRetrieveResponse(UsedItemPost post, UsedItemPostCounter counter, RetrieveResponse seller,
            String authId){
        this.postId = post.getUsedItemPostId();
        this.suggestCount = counter.getSuggestCount();
        this.interestCount = counter.getInterestCount();
        this.viewCount = counter.getViewCount();
        this.chatCount = counter.getChatCount();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.category = post.getCategory();
        this.createdAt = post.getCreatedAt();
        this.isHide = post.getIsHide();
        this.price = post.getPrice();
        this.canSuggest = post.getCanSuggest();
        this.wishX = post.getWishX();
        this.wishY = post.getWishY();
        this.wishText = post.getWishText();
        this.townId = post.getTownId();
        this.townAddress = post.getAddress();
        this.sellerId =seller.getAuthId();
        this.sellerNickname = seller.getNickname();
        this.sellerMannerTemperature = seller.getMannerTemperature();
        this.isMine = post.validateSellerId(authId);
        this.picturesUrl = post.getPicturesUrl();
        this.tradeStatus = post.getTradeStatus();

    }

    public static PostRetrieveResponse of (UsedItemPost post, UsedItemPostCounter counter, RetrieveResponse seller, String authId){
        return new PostRetrieveResponse(post, counter, seller, authId );

    }

}
