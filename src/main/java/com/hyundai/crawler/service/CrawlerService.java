package com.hyundai.crawler.service;

import com.hyundai.crawler.common.util.StringUtil;
import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.dto.CrawlingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final CrawlingValidService crawlingValidService;
    private final CrawlingHandlerService crawlingHandlerService;

    /**
     * 크롤링 요청 및 데이터 정렬
     *
     * @param crawlingRequestDto
     * @return crawlingResponseDto
     */
    @Cacheable(cacheNames = "crawling", key = "#crawlingRequestDto.urlList")
    public CrawlingResponseDto getCrawling(CrawlingRequestDto crawlingRequestDto) {
        // urlList 유효성 검증
        crawlingValidService.validUrlList(crawlingRequestDto);

        //요청 웹사이트 크롤링
        List<CompletableFuture<String>> completableFutureList = new ArrayList<>();
        for (String url : crawlingRequestDto.getUrlList()) {
            completableFutureList.add(crawlingHandlerService.webCrawling(url));
        }
        //크롤링문자열 merge
        String crawlingText = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.joining());
        //영문,숫자 parsing
        String parseText = StringUtil.alphabetAndNumberParser(crawlingText);
        //중복문자 제거
        Set<Character> uniqueCharSet = StringUtil.removeDuplicateCharacters(parseText);
        //오름차순 정렬
        List<Character> sortCharList = StringUtil.ascendingSort(uniqueCharSet);
        //조건에 맞춰 문자열 정렬(Aa0Bb1C2d3...)
        String crossText = StringUtil.crossAlphabetAndNumber(sortCharList);

        return CrawlingResponseDto.of(crossText);
    }
}