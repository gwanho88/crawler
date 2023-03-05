package com.hyundai.crawler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.dto.CrawlingResponseDto;
import com.hyundai.crawler.service.CrawlerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(CrawlerController.class)
public class CrawlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CrawlerService crawlerService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("크롤링 요청 성공")
    @Test
    public void getCrawlingRequestTest() throws Exception {
        //given
        List<String> urlTest = List.of("https://shop.hyundai.com/", "https://www.kia.com/", "https://www.genesis.com/");
        CrawlingRequestDto crawlingRequestDto = CrawlingRequestDto.of();
        crawlingRequestDto.setUrlList(urlTest);

        given(crawlerService.getCrawling(crawlingRequestDto)).willReturn(CrawlingResponseDto.of(""));

        //when
        ResultActions actions = mockMvc.perform(post("/crawling")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(crawlingRequestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isOk());
    }

    @DisplayName("크롤링 요청 필수값 누락 실패")
    @Test
    public void getCrawlingRequestFailTest() throws Exception {
        //given
        CrawlingRequestDto crawlingRequestDto = CrawlingRequestDto.of();
        crawlingRequestDto.setUrlList(List.of());
        //when
        ResultActions actions = mockMvc.perform(post("/crawling")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(crawlingRequestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isBadRequest());
    }
}
