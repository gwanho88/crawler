package com.hyundai.crawler.common.parser;

import com.hyundai.crawler.common.constants.RegexConstants;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 문자열 파싱
 */
public class CrawlingParser {
    private static final Pattern EXCLUDE_ALPHABET_NUMBER_PATTERN = Pattern.compile(RegexConstants.EXCLUDE_ALPHABET_NUMBER_REGEX);

    private static final String EMPTY = "";

    private String mergeText;

    public CrawlingParser(String mergeText) {
        this.mergeText = mergeText;
    }

    /**
     * 크롤링 데이터 parsing 및 교차 출력
     *
     * @return String 교차출력 데이터
     */
    public String parse() {
        //영문,숫자 parsing
        String parseText = this.parseAlphabetAndNumber(mergeText);
        //중복문자 제거
        Set<Character> uniqueCharSet = this.removeDuplicateCharacters(parseText);
        //오름차순 정렬
        List<Character> sortCharList = this.ascendingSort(uniqueCharSet);
        //조건에 맞춰 문자열 정렬(Aa0Bb1C2d3...)
        return this.crossAlphabetAndNumber(sortCharList);
    }

    /**
     * 문자열 영문,숫자 parsing
     *
     * @param text 문자열
     * @return String 알파벳과 숫자 문자열
     */
    private String parseAlphabetAndNumber(String text) {
        return EXCLUDE_ALPHABET_NUMBER_PATTERN.matcher(text).replaceAll(EMPTY);
    }

    /**
     * 문자열 중복문자 제거
     *
     * @param text 문자열
     * @return Set 중복문자 제거 Set
     */
    private Set<Character> removeDuplicateCharacters(String text) {
        final Set<Character> charsSet = new HashSet<>();
        text.chars().forEach(e -> charsSet.add((char) e));
        return charsSet;
    }

    /**
     * 오름차순 정렬
     *
     * @param charsSet  Character Set
     * @return List 정렬 Character List
     */
    private List<Character> ascendingSort(Set<Character> charsSet) {
        return charsSet.stream().sorted(new FirstUpperLetterComparator()).collect(Collectors.toList());
    }

    /**
     * 영문, 숫자 교차 출력
     *
     * @param charList Character List
     * @return String 교차출력 문자열
     */
    private String crossAlphabetAndNumber(List<Character> charList) {
        Queue<Character> alphabets = new LinkedList<>();
        Queue<Character> numbers = new LinkedList<>();
        this.divideAlphabetAndNumber(charList, alphabets, numbers);

        StringBuilder sb = new StringBuilder(alphabets.size() + numbers.size());
        while (!alphabets.isEmpty()|| !numbers.isEmpty()) {
            if (!alphabets.isEmpty()) {
                Character alphabet = alphabets.poll();
                sb.append(alphabet);
                Character peek = alphabets.peek();
                if (!ObjectUtils.isEmpty(peek) && Character.toUpperCase(alphabet) == Character.toUpperCase(peek)) {
                    sb.append(alphabets.poll());
                }
            }

            if (!numbers.isEmpty()) {
                sb.append(numbers.poll());
            }
        }

        return sb.toString();
    }

    /**
     * 영문, 숫자 문자 분류
     *
     * @param charList Character List
     * @param alphabets 알파벳 Queue
     * @param numbers 숫자 Queue
     */
    private void divideAlphabetAndNumber(List<Character> charList, Queue<Character> alphabets, Queue<Character> numbers) {
        for (Character c : charList) {
            if (Character.isLetter(c)) {
                alphabets.add(c);
            } else if (Character.isDigit(c)) {
                numbers.add(c);
            }
        }
    }
}
