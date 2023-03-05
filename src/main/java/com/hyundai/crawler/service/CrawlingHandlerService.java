package com.hyundai.crawler.service;

import com.hyundai.crawler.configuration.AsyncConfig;
import com.hyundai.crawler.exception.CrawlingException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;

/**
 * 크롤링 처리 Handler
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlingHandlerService {

    static final String USER_AGENT1 = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36\"";
    static final int TIMEOUT = 10000;

    /**
     * 크롤링 요청 및 데이터 정렬
     *
     * @param url
     * @return String
     */
    @Async(AsyncConfig.CRAWLING_ASYNC)
    public CompletableFuture<String> webCrawling(String url) {
        try {
            Connection.Response response = Jsoup
                    .connect(url)
                    .userAgent(USER_AGENT1)
                    .timeout(TIMEOUT)
                    .method(Connection.Method.GET)
                    .followRedirects(true)
                    .execute();
            log.info(response.body());
            return CompletableFuture.completedFuture(response.body());
        } catch (UnknownHostException ex) {
            throw new CrawlingException(ExceptionCode.ERROR_CODE_1002, "(" + url + ")");
        } catch (Exception ex) {
            throw new CrawlingException(ExceptionCode.ERROR_CODE_1003, "(" + url + ")");
        }
    }
}
