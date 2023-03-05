package com.hyundai.crawler.common.crawler;

import com.hyundai.crawler.exception.CrawlingException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 크롤링 처리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Crawler {

    private static final String USER_AGENT = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36\"";

    @Value("${crawler.timeout}")
    private int timeout;

    @Value("${crawler.redirection}")
    private boolean redirection;

    /**
     * 크롤링 요청 및 데이터 정렬
     *
     * @param url 크롤링 요청 url
     * @return String 크롤링 데이터
     */
    public String crawling(String url) {
        try {
            Connection.Response response = Jsoup
                    .connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(timeout)
                    .method(Connection.Method.GET)
                    .followRedirects(redirection)
                    .execute();
            return response.body();
        } catch (UnknownHostException ex) {
            log.error("CrawlingException : url = {}, ex = ", url, ex);
            throw new CrawlingException(ExceptionCode.ERROR_CODE_4002);
        } catch (SocketTimeoutException ex) {
            log.error("CrawlingException : url = {}, ex = ", url, ex);
            throw new CrawlingException(ExceptionCode.ERROR_CODE_4003);
        } catch (Exception ex) {
            log.error("CrawlingException : url = {}, ex = ", url, ex);
            throw new CrawlingException(ExceptionCode.ERROR_CODE_4004);
        }
    }
}
