package com.hyundai.crawler.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 캐시 설정
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

}
