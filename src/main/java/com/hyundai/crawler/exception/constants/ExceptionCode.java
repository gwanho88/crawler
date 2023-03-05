package com.hyundai.crawler.exception.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * error code 정의
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    SYS_ERROR_CODE_101("101", HttpStatus.INTERNAL_SERVER_ERROR, "시스템 에러 : 관리자에게 문의해 주세요."),
    SYS_ERROR_CODE_102("102", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 Method 입니다."),

    ERROR_CODE_1001("1001", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 주소를 입력하셨습니다. 입력한 정보를 확인해 주세요."),
    ERROR_CODE_1002("1002", HttpStatus.UNPROCESSABLE_ENTITY, "요청한 주소는 접근할 수 없는 주소입니다."),
    ERROR_CODE_1003("1003", HttpStatus.UNPROCESSABLE_ENTITY, "요청한 주소를 크롤링하지 못했습니다.")
    ;

    private final String code;

    private final HttpStatus httpStatus;

    private final String description;
}
