package com.hyundai.crawler.common.util;

import com.hyundai.crawler.common.constants.RegexConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 문자열 유틸
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    private static final Pattern EXCLUDE_NUMBER_ALPHABET_PATTERN = Pattern.compile(RegexConstants.EXCLUDE_ALPHABET_NUMBER_REGEX);

    static final String EMPTY = "";

    /**
     * 문자열 영문,숫자 parsing
     *
     * @param text
     * @return String
     */
    public static String alphabetAndNumberParser(String text) {
        return EXCLUDE_NUMBER_ALPHABET_PATTERN.matcher(text).replaceAll(EMPTY);
    }

    /**
     * 문자열 중복문자 제거
     *
     * @param text
     * @return Set
     */
    public static Set<Character> removeDuplicateCharacters(String text) {
        Set<Character> charsSet = new HashSet<>();
        text.chars().forEach(e -> charsSet.add((char) e));
        return charsSet;
    }

    /**
     * 오름차순 정렬
     *
     * @param charsSet
     * @return List
     */
    public static List<Character> ascendingSort(Set<Character> charsSet) {
        return charsSet.stream().sorted(getCharacterComparator()).collect(Collectors.toList());
    }

    /**
     * 영문, 숫자 교차 출력
     *
     * @param charsSet
     * @return String
     */
    public static String crossAlphabetAndNumber(List<Character> charsSet) {
        Queue<Character> alphabets = charsSet.stream().filter(Character::isLetter).collect(Collectors.toCollection(LinkedList::new));
        Queue<Character> numbers = charsSet.stream().filter(Character::isDigit).collect(Collectors.toCollection(LinkedList::new));
        StringBuilder sb = new StringBuilder(alphabets.size() + numbers.size());

        while (alphabets.size() != 0 || numbers.size() != 0) {
            if (alphabets.size() != 0) {
                Character alphabet = alphabets.poll();
                sb.append(alphabet);
                if (alphabets.size() != 0 && Character.toUpperCase(alphabet) == Character.toUpperCase(alphabets.peek())) {
                    sb.append(alphabets.poll());
                }
            }

            if (numbers.size() != 0) {
                sb.append(numbers.poll());
            }
        }

        return sb.toString();
    }

    private static Comparator<Character> getCharacterComparator() {
        return (o1, o2) -> {
            Character upperC1 = Character.toUpperCase(o1);
            Character upperC2 = Character.toUpperCase(o2);
            int result = upperC1.compareTo(upperC2);
            return (result == 0) ? o1.compareTo(o2) : result;
        };
    }
}
