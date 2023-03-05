package com.hyundai.crawler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlingRequestDto {

    @Schema(description = "크롤링 요청 url 목록", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "필수입력정보(urlList)를 입력해주세요.")
    @Size(min = 1, message = "크롤링할 주소를 최소 1개이상 입력해주세요.(urlList)")
    private List<String> urlList;

    public static CrawlingRequestDto of() {
        return new CrawlingRequestDto();
    }
}
