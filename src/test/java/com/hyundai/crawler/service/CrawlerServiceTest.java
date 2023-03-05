package com.hyundai.crawler.service;

import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.dto.CrawlingResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CrawlerServiceTest {

    @InjectMocks
    private CrawlerService crawlerService;

    @Mock
    private CrawlingHandlerService crawlingHandlerService;

    @Mock
    CrawlingValidService crawlingValidService;

    @Test
    @DisplayName("크롤링 및 문자 정렬 수행")
    void getCrawlingTest() {
        //given
        CrawlingRequestDto crawlingRequestDto = CrawlingRequestDto.of();
        List<String> urlList = List.of("https://shop.hyundai.com/", "https://www.kia.com/", "https://www.genesis.com/");
        crawlingRequestDto.setUrlList(urlList);

        String crawlingData = "html124divABCDefgtaBleImg1";
        String resultCrossText = "Aa1B2C4DdefghIilmtv";

        given(crawlingHandlerService.webCrawling(any())).willReturn(CompletableFuture.completedFuture(crawlingData));

        //when
        CrawlingResponseDto actualDto = crawlerService.getCrawling(crawlingRequestDto);

        //then
        Assertions.assertEquals( resultCrossText, actualDto.getMerge());
    }
}
