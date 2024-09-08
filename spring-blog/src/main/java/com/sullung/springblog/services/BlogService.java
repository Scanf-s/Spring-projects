package com.sullung.springblog.services;

import com.sullung.springblog.dto.AddPostRequestDto;
import com.sullung.springblog.dto.UpdatePostRequestDto;
import com.sullung.springblog.models.Post;
import com.sullung.springblog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Post savePost(AddPostRequestDto addPostRequestDto) {
        return blogRepository.save(addPostRequestDto.toEntity());
    }

    public List<Post> getPosts() {
        return blogRepository.findAll();
    }

    public Post getPost(Long id) {
        return blogRepository.findPostById(id);
    }

    public void deletePost(Long id) {
        blogRepository.deletePostById(id);
    }

    public Post updatePost(Long id, UpdatePostRequestDto updatePostRequestDto) {
        Post post = blogRepository.findPostById(id);
        post.update(updatePostRequestDto);
        return post;
    }
}
