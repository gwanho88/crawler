package com.hyundai.crawler.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtilTest {

    @DisplayName("크롤링 데이터 영문,숫자 파싱")
    @Test
    public void alphabetAndNumberParserTest() {
        //given
        String text = "<html>11243346<div>AbCdefgHij</div><html>";
        String expected = "html11243346divAbCdefgHijdivhtml";

        //when
        String actual = StringUtil.alphabetAndNumberParser(text);
        System.out.println("parsingText : " + actual);

        // then
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("중복 문자 제거")
    @Test
    public void removeDuplicateCharactersTest() {
        //given
        String text = "html11243346divAbCdefgHijdivhtml";
        int expectedSize = 20;

        //when
        Set<Character> actual = StringUtil.removeDuplicateCharacters(text);

        // then
        Assertions.assertEquals(expectedSize, actual.size());
    }

    @DisplayName("문자 오름차순 정렬(124AaBb..)")
    @Test
    public void ascendingSortTest() {
        //given
        Set<Character> characters = "html124divABCDefgaI".chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        String expected = "124AaBCDdefghIilmtv";

        //when
        String actual = StringUtil.ascendingSort(characters).stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        // then
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("문자숫자 교차 출력(Aa1B2Cc3..)")
    @Test
    public void crossAlphabetAndNumberTest() {
        //given
        List<Character> characters = "AaBCDdefghIilmtv124".chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        String expected = "Aa1B2C4DdefghIilmtv";

        //when
        String actual = StringUtil.crossAlphabetAndNumber(characters);

        // then
        Assertions.assertEquals(expected, actual);
    }

}
