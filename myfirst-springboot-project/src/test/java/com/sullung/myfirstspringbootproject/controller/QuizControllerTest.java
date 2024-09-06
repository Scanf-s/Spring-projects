package com.sullung.myfirstspringbootproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper; // 클래스 객체와 JSON 매핑하기위해 사용함 (직렬화, 역직렬화)

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("QUIZ(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created 리턴")
    @Test
    public void quiz() throws Exception {
        //given
        final String url = "/quiz?code=1";

        //when
        final ResultActions result = mockMvc
                .perform(get(url).accept(MediaType.APPLICATION_JSON));

        //then
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created"));
    }

    @DisplayName("QUIZ2(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request")
    @Test
    public void quiz2() throws Exception {
        //given
        final String url = "/quiz?code=2";

        //when
        final ResultActions result = mockMvc
                .perform(get(url).accept(MediaType.APPLICATION_JSON));

        //then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request"));
    }

    @DisplayName("QUIZ3() : POST /quiz?code=1 이면 응답코드는 403, 응답 본문은 Forbidden")
    @Test
    public void quiz3() throws Exception {
        //given
        final String url = "/quiz?code=1";

        //when
        final ResultActions result = mockMvc
                .perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Code(1))));

        //then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden"));
    }

    @DisplayName("QUIZ4() : POST /quiz?code=13 이면 응답코드는 200, 응답 본문은 OK")
    @Test
    public void quiz4() throws Exception {
        //given
        final String url = "/quiz?code=13";

        //when
        final ResultActions result = mockMvc
                .perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Code(13))));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

}