package com.hyundai.crawler.exception.dto;

import com.hyundai.crawler.exception.constants.ExceptionCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ExceptionDto {

    private final int errHttpStatus;
    private final String errMsg;

    public static ExceptionDto of(final ExceptionCode exceptionCode) {
        return new ExceptionDto(exceptionCode.getHttpStatus().value(), exceptionCode.getDescription());
    }

    public static ExceptionDto of(final int errHttpStatus, final String errMsg) {
        return new ExceptionDto(errHttpStatus, errMsg);
    }
}
