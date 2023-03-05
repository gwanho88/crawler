package com.hyundai.crawler.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "async-task")
public class AsyncTaskProperties {

    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
}
