package com.gaaji.useditem.domain;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsedItemPicture> pictures = new ArrayList<>();

    public static UsedItemPost of(UsedItemPostId postId, SellerId sellerId, Post post,
    Price price, boolean canSuggest, WishPlace wishPlace,
            TradeStatus tradeStatus,PurchaserId purchaserId, Town town, List<UsedItemPicture> pictures){
        return new UsedItemPost(postId,sellerId,post,price
                ,canSuggest,wishPlace
                ,tradeStatus,purchaserId
                ,town,pictures);
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
}
