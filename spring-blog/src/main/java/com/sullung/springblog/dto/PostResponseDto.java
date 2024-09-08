package com.sullung.springblog.dto;

import com.sullung.springblog.models.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private final String title;
    private final String content;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
