package com.gaaji.useditem.domain;

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
public class UsedItemPicture {

    @EmbeddedId
    private UsedItemPictureId pictureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private UsedItemPost post;

    private String url;


    public static UsedItemPicture of(UsedItemPictureId pictureId,
            String url){
        return new UsedItemPicture(pictureId,null,url);
    }

    public String getUsedItemPictureId() {
        return pictureId.getId();
    }

    public void associateWithPost(UsedItemPost usedItemPost) {
        this.post = usedItemPost;
    }
}
