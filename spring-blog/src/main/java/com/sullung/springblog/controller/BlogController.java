package com.sullung.springblog.controller;

import com.sullung.springblog.dto.AddPostRequestDto;
import com.sullung.springblog.dto.PostResponseDto;
import com.sullung.springblog.dto.UpdatePostRequestDto;
import com.sullung.springblog.models.Post;
import com.sullung.springblog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 자동으로 HTTP 응답으로 객체 데이터를 JSON으로 변환하는 기능이 추가됨
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/post")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequestDto addPostRequest) {
        Post newPost = blogService.savePost(addPostRequest); // 여기서는 DTO만 넘겨주고, blogService 객체에서 Entity 변환 후 저장
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> posts = blogService.getPosts()
                .stream()
                .map(PostResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(posts);
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        Post post = blogService.getPost(id);
        return ResponseEntity.ok().body(new PostResponseDto(post));
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        blogService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        Post updatedPost = blogService.updatePost(id, updatePostRequestDto);
        return ResponseEntity.ok().body(updatedPost);
    }

}
