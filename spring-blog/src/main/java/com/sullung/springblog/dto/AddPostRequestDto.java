package com.sullung.springblog.dto;

import com.sullung.springblog.models.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPostRequestDto {

    private String title;
    private String content;

    public Post toEntity() { // DTO를 Entity로 변환하는 메서드
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}
