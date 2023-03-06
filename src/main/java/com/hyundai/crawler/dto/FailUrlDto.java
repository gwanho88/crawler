package com.hyundai.crawler.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 크롤링 실패 정보 dto
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FailUrlDto {

    private String url;

    private String errorMsg;

    private FailUrlDto(String url, String errorMsg) {
        this.url = url;
        this.errorMsg = errorMsg;
    }

    public static FailUrlDto of(String url) {
        return new FailUrlDto(url, "");
    }

    public static FailUrlDto of(String url, String errorMsg) {
        return new FailUrlDto(url, errorMsg);
    }
}
