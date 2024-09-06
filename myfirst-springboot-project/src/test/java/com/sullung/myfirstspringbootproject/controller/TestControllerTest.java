package com.sullung.myfirstspringbootproject.controller;

import com.sullung.myfirstspringbootproject.model.Member;
import com.sullung.myfirstspringbootproject.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Test용 application context
@AutoConfigureMockMvc // MockMvc 생성 및 auto configuration
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc; // 테스트용 MVC 환경을 만들어서 Request, Response 기능을 제공함

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception{
        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member("Kim"));

        //when
        final ResultActions result = mockMvc
                .perform(get(url).accept(MediaType.APPLICATION_JSON)); // Request with GET Method, Content-Type: application/json

        //then
        result
                .andExpect(status().isOk()) // Is response status 200 OK?
                .andExpect(jsonPath("$[0].id").value(savedMember.getId())) // Is response body contains member id
                .andExpect(jsonPath("$[0].name").value(savedMember.getName())); // Is response body contains member name
    }
}