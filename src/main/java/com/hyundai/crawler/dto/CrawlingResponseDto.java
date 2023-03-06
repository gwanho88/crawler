package com.hyundai.crawler.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 크롤링 response dto
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlingResponseDto {

    private String merge;

    private List<FailUrlDto> crawlingFailUrlList;

    private CrawlingResponseDto(String merge, List<FailUrlDto> crawlingFailUrlList) {
        this.merge = merge;
        this.crawlingFailUrlList = crawlingFailUrlList;
    }

    public static CrawlingResponseDto of(final String merge, final List<FailUrlDto> crawlingFailUrlList) {
        return new CrawlingResponseDto(merge, crawlingFailUrlList);
    }
}
