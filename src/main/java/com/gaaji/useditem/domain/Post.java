package com.gaaji.useditem.domain;

import com.gaaji.useditem.exception.InputNullDataOnCategoryException;
import com.gaaji.useditem.exception.InputNullDataOnTitleException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Access(AccessType.FIELD)
@Getter
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
    private Post(String title, String contents, String category, LocalDateTime createdAt, boolean isHide) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.createdAt = createdAt;
        this.isHide = isHide;
    }
    public static Post of(String title, String contents, String category){
        if(!StringUtils.hasText(title)) throw new InputNullDataOnTitleException();
        if(!StringUtils.hasText(category)) throw new InputNullDataOnCategoryException();

        return new Post(title,contents,category);
    }
    public Post modify(String title, String contents, String category, boolean isHide){
        if(!StringUtils.hasText(title)) throw new InputNullDataOnTitleException();
        if(!StringUtils.hasText(category)) throw new InputNullDataOnCategoryException();


        return new Post(title,contents,category,this.createdAt,isHide);
    }
    public void reverseHide(){
        isHide = !isHide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return isHide == post.isHide && Objects.equals(title, post.title)
                && Objects.equals(contents, post.contents) && Objects.equals(
                category, post.category) && Objects.equals(createdAt, post.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents, category, createdAt, isHide);
    }
	public boolean getIsHide() {
		return isHide;
	}
}
