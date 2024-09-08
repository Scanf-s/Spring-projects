package com.sullung.springblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sullung.springblog.dto.AddPostRequestDto;
import com.sullung.springblog.dto.UpdatePostRequestDto;
import com.sullung.springblog.models.Post;
import com.sullung.springblog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    @DisplayName("addPost() - POST /api/post : 블로그 글 추가하는 기능 테스트")
    @Test
    public void addPost() throws Exception {

        //given
        final String uri = "/api/post";
        final String title = "title";
        final String content = "content";
        final AddPostRequestDto newRequest = new AddPostRequestDto(title, content);

        // Serialize
        final String requestBody = objectMapper.writeValueAsString(newRequest);

        //when
        ResultActions result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Post> posts = blogRepository.findAll();

        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("getPosts() - GET /api/posts : 블로그 글 리스트 GET 테스트")
    @Test
    public void getPosts() throws Exception {
        //given
        final String uri = "/api/posts";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Post.builder().title(title).content(content).build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }

    @DisplayName("getPost() - GET /api/post/{id} : 블로그 글 GET 테스트")
    @Test
    public void getPost() throws Exception {
        //given
        final String uri = "/api/post/{id}";
        final String title = "title";
        final String content = "content";

        final Post post = blogRepository.save(Post.builder().title(title).content(content).build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(uri, post.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("deletePost() - DELETE /api/post/{id} : 블로그 글 삭제 테스트")
    @Test
    public void deletePost() throws Exception {
        //given
        final String uri = "/api/post/{id}";
        final String title = "title";
        final String content = "content";

        final Post post = blogRepository.save(Post.builder().title(title).content(content).build());

        //when
        final ResultActions resultActions = mockMvc.perform(delete(uri, post.getId()));

        //then
        resultActions
                .andExpect(status().isNoContent());

        List<Post> posts = blogRepository.findAll();

        assertThat(posts).hasSize(0);
    }

    @DisplayName("updatePost() - PUT /api/post/{id} : 블로그 글 수정 테스트")
    @Test
    public void updatePost() throws Exception {
        //given
        final String uri = "/api/post/{id}";
        final String title = "title";
        final String content = "content";
        final String newTitle = "newTitle";
        final String newContent = "newContent";

        Post post = blogRepository.save(Post.builder().title(title).content(content).build());
        UpdatePostRequestDto requestBody = new UpdatePostRequestDto(newTitle, newContent);

        //when
        ResultActions resultActions = mockMvc
                .perform(
                        put(uri, post.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(requestBody))
                );

        //then
        resultActions.andExpect(status().isOk());

        Post updatedPost = blogRepository.findPostById(post.getId());

        assertThat(updatedPost.getTitle()).isEqualTo(newTitle);
        assertThat(updatedPost.getContent()).isEqualTo(newContent);

    }

}