package com.hyundai.crawler.exception.advice;

import com.hyundai.crawler.exception.BadRequestException;
import com.hyundai.crawler.exception.CrawlingException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import com.hyundai.crawler.exception.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.error("Internal Server Error {}", ex.getStackTrace());

        return ExceptionDto.of(ExceptionCode.SYS_ERROR_CODE_101);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ExceptionDto.of(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleBadRequestException(BadRequestException ex) {
        return ExceptionDto.of(ex.getExceptionCode().getHttpStatus().value(), ex.getExceptionCode().getDescription() + ex.getErrorMsg());
    }

    @ExceptionHandler(CrawlingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionDto handleBadRequestException(CrawlingException ex) {
        log.error("CrawlingException msg : {}", ex.getStackTrace());
        return ExceptionDto.of(ex.getExceptionCode().getHttpStatus().value(), ex.getExceptionCode().getDescription() + ex.getErrorMsg());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ExceptionDto.of(ExceptionCode.SYS_ERROR_CODE_102);
    }
}
