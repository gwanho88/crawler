package com.hyundai.crawler.dto;

import com.hyundai.crawler.common.constants.RegexConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class CrawlingRequestDto {

    @Schema(description = "크롤링 url 리스트")
    @NotEmpty(message = "크롤링할 주소를 최소 1개이상 입력해주세요.")
    private List<@URL(regexp = RegexConstants.URL_REGEX, message = "형식에 맞지 않는 url을 입력하셨습니다. 입력한 정보를 확인해 주세요.") String> urlList;
}
