package com.hyundai.crawler.service;

import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.exception.BadRequestException;
import com.hyundai.crawler.exception.constants.ExceptionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class CrawlingValidServiceTest {

    @InjectMocks
    private CrawlingValidService crawlingValidService;

    @Test
    @DisplayName("크롤링 할 주소 유효성 체크 실패(형식에 맞지 않음)")
    void validUrlFailTest() {
        //given
        CrawlingRequestDto crawlingRequestDto = CrawlingRequestDto.of();
        List<String> urlList = List.of("shop.hyundai.com/", "www.kia.com/", "www.genesis.com/");
        crawlingRequestDto.setUrlList(urlList);

        //when
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> crawlingValidService.validUrlList(crawlingRequestDto));

        //then
        Assertions.assertEquals(ExceptionCode.ERROR_CODE_1001, exception.getExceptionCode());
    }
}
