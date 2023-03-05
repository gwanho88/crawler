package com.hyundai.crawler.exception;

import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.Getter;

/**
 * 크롤링 수행 실패 Exception
 */
@Getter
public class CrawlingException extends RuntimeException {

    private ExceptionCode exceptionCode;

    private String errorMsg = "";

    public CrawlingException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public CrawlingException(ExceptionCode exceptionCode, String errorMsg) {
        this.exceptionCode = exceptionCode;
        this.errorMsg = errorMsg;
    }
}
