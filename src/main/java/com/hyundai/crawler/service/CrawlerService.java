package com.hyundai.crawler.service;

import com.hyundai.crawler.common.crawler.Crawler;
import com.hyundai.crawler.common.parser.CrawlingParser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final Crawler crawler;

    /**
     * crawling 요청
     *
     * @param url crawling 요청 url
     * @return String crawling 데이터
     */
    @Cacheable(cacheNames = "crawling")
    public String crawlingRequest(final String url) {
        return crawler.crawling(url);
    }

    /**
     * crawling 데이터 파싱(정렬 및 교차 출력)
     *
     * @param mergeText 머지한 crawling 데이터
     * @return String 파싱 및 정렬된 문자열
     */
    public String parseCrawlingData(String mergeText) {
        CrawlingParser crawlingParser = new CrawlingParser(mergeText);
        return crawlingParser.parse();
    }
}