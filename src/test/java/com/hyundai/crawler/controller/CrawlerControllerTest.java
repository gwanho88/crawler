package com.hyundai.crawler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.crawler.dto.CrawlingRequestDto;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(CrawlerController.class)
class CrawlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CrawlerService crawlerService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("크롤링 요청 성공")
    @Test
    void crawlingRequestTest() throws Exception {
        //given
        List<String> urlTest = List.of("https://shop.hyundai.com/", "https://www.kia.com/", "https://www.genesis.com/");
        CrawlingRequestDto crawlingRequestDto = new CrawlingRequestDto();
        crawlingRequestDto.setUrlList(urlTest);

        given(crawlerService.crawlingRequest(anyString())).willReturn("test");
        given(crawlerService.parseCrawlingData(anyString())).willReturn("test");

        //when
        ResultActions actions = mockMvc.perform(post("/crawling")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(crawlingRequestDto)))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.merge").isNotEmpty());
    }

    @DisplayName("크롤링 요청 필수값 누락 실패")
    @Test
    void crawlingEmptyUrlRequestFailTest() throws Exception {
        //given
        CrawlingRequestDto crawlingRequestDto = new CrawlingRequestDto();
        crawlingRequestDto.setUrlList(List.of());
        //when
        ResultActions actions = mockMvc.perform(post("/crawling")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(crawlingRequestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isBadRequest());
    }

    @DisplayName("크롤링 요청 필수값 누락 실패")
    @Test
    void crawlingWrongUrlRequestFailTest() throws Exception {
        //given
        List<String> urlTest = List.of("ht://shop.hyundai.com/", "ps://www.kia.com/", "httpswww.genesis.com/");
        CrawlingRequestDto crawlingRequestDto = new CrawlingRequestDto();
        crawlingRequestDto.setUrlList(urlTest);
        //when
        ResultActions actions = mockMvc.perform(post("/crawling")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(crawlingRequestDto)))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest());
    }
}
