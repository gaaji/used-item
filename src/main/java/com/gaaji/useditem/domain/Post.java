package com.gaaji.useditem.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Post {

    private String title;
    private String contents;
    private String category;
    private LocalDateTime createdAt;
    private boolean isHide;

    private Post(String title, String contents, String category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.isHide = false;
    }
    public static Post of(String title, String contents, String category){
        return new Post(title,contents,category);
    }

    public void reverseHide(){
        isHide = !isHide;
    }

	public boolean getIsHide() {
		return isHide;
	}

}
