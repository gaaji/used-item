package com.gaaji.useditem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UsedItemPost {

    @EmbeddedId
    private UsedItemPostId postId;

    @AttributeOverride(name = "id", column = @Column(name = "seller_id"))
    @Embedded
    private SellerId sellerId;

    @Embedded
    private Post post;

    @Embedded
    private Price price;

    private boolean canSuggest;

    @Embedded
    private WishPlace wishPlace;

    @Enumerated(EnumType.STRING)
    private TradeStatus tradeStatus;

    @AttributeOverride(name = "id", column = @Column(name = "purchaser_id"))
    @Embedded
    private PurchaserId purchaserId;

    @AttributeOverride(name = "id", column = @Column(name = "town_id"))
    @Embedded
    private Town town;
    
    private String representPictureUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsedItemPicture> pictures = new ArrayList<>();

    public static UsedItemPost of(UsedItemPostId postId, SellerId sellerId, Post post,
    Price price, boolean canSuggest, WishPlace wishPlace,
             Town town, List<UsedItemPicture> pictures){
        return new UsedItemPost(postId,sellerId,post,price
                ,canSuggest,wishPlace
                ,TradeStatus.SELLING,PurchaserId.of(null)
                ,town, null, pictures);
    }
    // 글 내용이 바뀐다.
    public void modify(Post post, Price price, boolean canSuggest,WishPlace wishPlace,
            List<UsedItemPicture> pictures){

        this.post = post;
        this.price = price;
        this.canSuggest =canSuggest;
        this.wishPlace = wishPlace;
        this.pictures = pictures;
    }

    public void reverseHide(){
        post.reverseHide();
    }

    public String getUsedItemPostId() {
        return postId.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsedItemPost that = (UsedItemPost) o;
        return canSuggest == that.canSuggest && Objects.equals(postId, that.postId)
                && Objects.equals(sellerId, that.sellerId) && Objects.equals(post,
                that.post) && Objects.equals(price, that.price) && Objects.equals(
                wishPlace, that.wishPlace) && tradeStatus == that.tradeStatus
                && Objects.equals(purchaserId, that.purchaserId) && Objects.equals(
                town, that.town) && Objects.equals(pictures, that.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, sellerId, post, price, canSuggest, wishPlace, tradeStatus,
                purchaserId, town, pictures);
    }
	public boolean checkSellerId(String authId) {
		return this.sellerId.getId().equals(authId);
	}

	public boolean getIsHide() {
		return post.getIsHide();
	}

}
