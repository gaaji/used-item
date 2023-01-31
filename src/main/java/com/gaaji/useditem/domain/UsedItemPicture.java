package com.gaaji.useditem.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UsedItemPicture implements Comparable<UsedItemPicture>{

    @EmbeddedId
    private UsedItemPictureId pictureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private UsedItemPost post;

    private String url;
    @Column(name = "picture_order")
    private Integer order;


    public static UsedItemPicture of(UsedItemPictureId pictureId,
            String url){
        return new UsedItemPicture(pictureId,null,url,null);
    }

    public String getUsedItemPictureId() {
        return pictureId.getId();
    }

    public void associateWithPost(UsedItemPost usedItemPost, Integer order) {
        this.post = usedItemPost;
        this.order = order;
    }

    public void changeOrder(Integer order){
        this.order = order;
    }


    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || String.class != o.getClass()) {
            return false;
        }
        String that = (String) o;
        return Objects.equals(url, that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public int compareTo(UsedItemPicture o) {
        return this.order.compareTo(o.getOrder());
    }

    private Integer getOrder() {
        return order;
    }
}
