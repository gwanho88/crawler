package com.hyundai.crawler.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 정규식 상수
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexConstants {

    public static final String EXCLUDE_ALPHABET_NUMBER_REGEX = "[^a-zA-Z0-9]";

    public static final String URL_REGEX = "(https?:\\/\\/)([a-zA-Z0-9-.]+)(\\S+)?";
}
