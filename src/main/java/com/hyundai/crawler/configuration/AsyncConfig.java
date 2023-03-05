package com.hyundai.crawler.configuration;

import com.hyundai.crawler.configuration.properties.AsyncTaskProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 비동기(멀티쓰레드) 설정
 */
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class AsyncConfig {
    private final AsyncTaskProperties asyncTaskProperties;

    public ThreadPoolTaskExecutor threadPoolTaskExecutor(int corePoolSize, int maxPoolSize, int queueCapacity, String threadNamePrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public Executor crawlingExecutor() {
        return threadPoolTaskExecutor(
                asyncTaskProperties.getCorePoolSize(),
                asyncTaskProperties.getMaxPoolSize(),
                asyncTaskProperties.getQueueCapacity(),
                "Crawling TaskExecutor-"
        );
    }
}
