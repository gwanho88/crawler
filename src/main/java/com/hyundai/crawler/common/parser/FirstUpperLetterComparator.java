package com.hyundai.crawler.common.parser;

import java.util.Comparator;

/**
 * 문자열 정렬
 */
public class FirstUpperLetterComparator implements Comparator<Character> {
    @Override
    public int compare(Character o1, Character o2) {
        Character upperC1 = Character.toUpperCase(o1);
        Character upperC2 = Character.toUpperCase(o2);
        int result = upperC1.compareTo(upperC2);
        return (result == 0) ? o1.compareTo(o2) : result;
    }
}
