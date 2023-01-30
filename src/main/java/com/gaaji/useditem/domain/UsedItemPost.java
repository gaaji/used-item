package com.gaaji.useditem.domain;

import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.ReservationStatusChangePriceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
             Town town){
        return new UsedItemPost(postId,sellerId,post,price
                ,canSuggest,wishPlace
                ,TradeStatus.SELLING,PurchaserId.of(null)
                ,town, null, new ArrayList<>());
    }
    // 글 내용이 바뀐다.
    public void modify(SellerId authId, PostUpdateRequest updateDto){

        validateCanModifyPost(authId, updateDto);

        this.post = post.modify(updateDto.getTitle(), updateDto.getContents(), updateDto.getCategory(),
                updateDto.getIsHide());
        this.price = Price.of(updateDto.getPrice());
        this.canSuggest = updateDto.getCanSuggest();
        this.wishPlace = WishPlace.of(updateDto.getWishX(),updateDto.getWishY(),updateDto.getWishText());


        updatePicture(updateDto.getUrls());

    }

    private void validateCanModifyPost(SellerId authId, PostUpdateRequest updateDto) {
        validateSellerId(authId);
        validateTradeStatus(updateDto);
    }

    private void validateTradeStatus(PostUpdateRequest updateDto) {
        if(tradeStatus==TradeStatus.RESERVATION && updateDto.getPrice() != getPrice())
            throw new ReservationStatusChangePriceException();
    }

    private void validateSellerId(SellerId authId) {
        if(!this.sellerId.equals(authId))
            throw new NoMatchAuthIdAndSellerIdException();
    }

    private void updatePicture(List<String> urls){
            List<UsedItemPicture> changedPictures = new ArrayList<>();
        for (String url : urls)
            for (UsedItemPicture picture : pictures)
                if(picture.equals(url))
                    changedPictures.add(picture);
        this.pictures.clear();
        this.pictures.addAll(changedPictures);
        pictures.forEach(p -> p.changeOrder(pictures.indexOf(p)));
        setRepresentPictureUrl();
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
	public boolean validateSellerId(String authId) {
		return this.sellerId.getId().equals(authId);
	}

	public boolean getIsHide() {
		return post.getIsHide();
	}

    public void addPictures(List<UsedItemPicture> list) {
        this.pictures.clear();
        this.pictures.addAll(list);
        this.pictures.forEach(p -> p.associateWithPost(this, pictures.indexOf(p)));
        setRepresentPictureUrl();
    }

    private void setRepresentPictureUrl() {
        if (pictures.isEmpty()) {
            this.representPictureUrl = null;
            return;
        }
        this.representPictureUrl = pictures.get(0).getUrl();
    }

    public void addPictures(List<UsedItemPicture> newPictures, int[] indexes) {
        int i = 0;
        for (int index : indexes) {
            this.pictures.add(index, newPictures.get(i++));
        }
        this.pictures.forEach(p -> p.associateWithPost(this, pictures.indexOf(p)));
        setRepresentPictureUrl();
    }

    public String getTitle() {
        return post.getTitle();
    }

    public String getContents() {
        return post.getContents();
    }

    public String getCategory() {
        return post.getCategory();
    }

    public LocalDateTime getCreatedAt() {
        return post.getCreatedAt();
    }

    public long getPrice() {
        return price.getPrice();
    }

    public boolean getCanSuggest() {
        return this.canSuggest;
    }

    public String getWishX() {
        return wishPlace.getPlaceX();
    }

    public String getWishY() {
        return wishPlace.getPlaceY();
    }

    public String getWishText() {
        return wishPlace.getPlaceText();
    }

    public String getTownId() {
        return town.getId();
    }

    public String getAddress() {
        return town.getAddress();
    }

    public List<String> getPicturesUrl(){
        Collections.sort(pictures);
        return this.pictures.stream().map(UsedItemPicture::getUrl)
                .toList();
    }


    public String getSellerId() {
        return this.sellerId.getId();
    }

    public String getRepresentPictureUrl(){
        return this.representPictureUrl;
    }


}
