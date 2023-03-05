package com.hyundai.crawler.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlingResponseDto implements Serializable {

    private static final long serialVersionUID = 1519494039163871526L;
    private String merge;

    private CrawlingResponseDto(String merge) {
        this.merge = merge;
    }

    public static CrawlingResponseDto of(final String merge) {
        return new CrawlingResponseDto(merge);
    }
}
