package com.hyundai.crawler.exception;

import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.Getter;

/**
 * 요청값이 잘못된 경우를 위한 Exception
 */
@Getter
public class BadRequestException extends RuntimeException {

    private ExceptionCode exceptionCode;

    private String errorMsg = "";

    public BadRequestException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public BadRequestException(ExceptionCode exceptionCode, String errorMsg) {
        this.exceptionCode = exceptionCode;
        this.errorMsg = errorMsg;
    }
}
