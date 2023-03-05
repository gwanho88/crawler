package com.hyundai.crawler.service;

import com.hyundai.crawler.common.constants.RegexConstants;
import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.exception.BadRequestException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * 크롤링 요청값 유효성체크
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlingValidService {

    private static final Pattern URL_PATTERN = Pattern.compile(RegexConstants.URL_REGEX);

    /**
     * 크롤링 요청 url 유효성 체크
     *
     * @param crawlingRequestDto
     */
    public void validUrlList(CrawlingRequestDto crawlingRequestDto) {
        for (String url : crawlingRequestDto.getUrlList()) {
            if (!URL_PATTERN.matcher(url).matches()) {
                throw new BadRequestException(ExceptionCode.ERROR_CODE_1001, "(" + url + ")");
            }
        }
    }
}
