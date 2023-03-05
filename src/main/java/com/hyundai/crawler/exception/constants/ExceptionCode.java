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

    SYS_ERROR_CODE_1001("1001", HttpStatus.INTERNAL_SERVER_ERROR, "시스템 에러 : 관리자에게 문의해 주세요."),
    SYS_ERROR_CODE_1002("1002", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 Method 입니다."),
    SYS_ERROR_CODE_1003("1003", HttpStatus.BAD_REQUEST, "잘못된 파라미터 타입으로 입력하셨습니다. 입력한 정보를 확인해 주세요."),

    ERROR_CODE_4001("4001", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 url을 입력하셨습니다. 입력한 정보를 확인해 주세요."),
    ERROR_CODE_4002("4002", HttpStatus.UNPROCESSABLE_ENTITY, "요청한 주소는 접근할 수 없는 주소입니다."),
    ERROR_CODE_4003("4003", HttpStatus.UNPROCESSABLE_ENTITY, "(timeout) 크롤링이 수행 되지 않았습니다."),
    ERROR_CODE_4004("4004", HttpStatus.UNPROCESSABLE_ENTITY, "요청한 주소를 크롤링하지 못했습니다.")
    ;

    private final String code;

    private final HttpStatus httpStatus;

    private final String description;
}
