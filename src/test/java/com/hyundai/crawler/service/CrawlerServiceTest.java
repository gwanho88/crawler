package com.hyundai.crawler.service;

import com.hyundai.crawler.common.crawler.Crawler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CrawlerServiceTest {

    @InjectMocks
    private CrawlerService crawlerService;
    @Mock
    private Crawler crawler;


    @Test
    @DisplayName("크롤링 수행")
    void crawlingRequestTest() {
        //given
        String url = "https://shop.hyundai.com/";

        String crawlingData = "html124divABCDefgtaBleImg1";

        given(crawler.crawling(any())).willReturn(crawlingData);

        //when
        String actual = crawlerService.crawlingRequest(url);

        //then
        assertThat(actual).isEqualTo(crawlingData);
    }

    @Test
    @DisplayName("크롤링 및 문자 정렬 수행")
    void parseCrawlingDataTest() {
        //given
        String crawlingData = "<html>11243346<div>AbCdefgHij</div><html>";
        String expected = "A1b2C3d4e6fgHhijlmtv";

        //when
        String actual = crawlerService.parseCrawlingData(crawlingData);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
