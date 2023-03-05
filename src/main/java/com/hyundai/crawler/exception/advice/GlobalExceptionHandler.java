package com.hyundai.crawler.exception.advice;

import com.hyundai.crawler.exception.BusinessException;
import com.hyundai.crawler.exception.CrawlingException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import com.hyundai.crawler.exception.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleDefaultException(Exception ex) {
        log.error("Internal Server Error : {}", ExceptionUtils.getStackTrace(ex));

        return ExceptionDto.of(ExceptionCode.SYS_ERROR_CODE_1001);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ExceptionDto.of(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ExceptionDto.of(ExceptionCode.SYS_ERROR_CODE_1002);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ExceptionDto.of(ExceptionCode.SYS_ERROR_CODE_1003);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleBusinessException(BusinessException ex) {
        return ExceptionDto.of(ex.getExceptionCode().getHttpStatus().value(), ex.getExceptionCode().getDescription() + ex.getErrorMsg());
    }

    @ExceptionHandler(CrawlingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionDto handleCrawlingException(CrawlingException ex) {
        log.error("CrawlingException msg : {}", ex.getStackTrace());
        return ExceptionDto.of(ex.getExceptionCode().getHttpStatus().value(), ex.getExceptionCode().getDescription() + ex.getErrorMsg());
    }
}
