package com.hyundai.crawler.common.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CrawlingParserTest {

    @DisplayName("크롤링 머지데이터 파싱 및 정렬 후 출력")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("webScrapRequestSampleData")
    void parseTest(String name, String crawlingData, String expected) {
        //when
        CrawlingParser crawlingParser = new CrawlingParser(crawlingData);
        String actual = crawlingParser.parse();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> webScrapRequestSampleData() {
        return Stream.of(
                Arguments.of("html코드", "<html>11243346<div>AbCdefgHij</div><html>", "A1b2C3d4e6fgHhijlmtv"),
                Arguments.of("특수문자 포함", "<html>1124#3346<di$v>AbCdef@g#Hij</di!v><ht&ml>", "A1b2C3d4e6fgHhijlmtv"),
                Arguments.of("숫자가 많을 때", "<htm>1928Bb3746A50<html>", "A0Bb1h2l3m4t56789"),
                Arguments.of("한글 및 공백 포함", "<html>11243한346<div>A글 bCdef테gH 스트ij</div><html>", "A1b2C3d4e6fgHhijlmtv")
        );
    }

}
