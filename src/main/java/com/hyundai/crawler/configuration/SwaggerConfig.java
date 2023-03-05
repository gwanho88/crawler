package com.hyundai.crawler.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 설정
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfig(SwaggerUiConfigProperties properties) {
        properties.setDefaultModelsExpandDepth(-1);
        properties.setDefaultModelExpandDepth(2);
        properties.setTryItOutEnabled(true);
        properties.setDisplayRequestDuration(true);
        properties.setDeepLinking(true);
        properties.getSyntaxHighlight().setActivated(true);
        properties.getSyntaxHighlight().setTheme("agate");
        return properties;
    }

    @Bean
    public OpenAPI openApi(@Value("v1.0") String version) {
        final Info info = new Info()
                .title("Crawler API ")
                .description("크롤링 API Doc입니다.")
                .version(version);
        return new OpenAPI().info(info);
    }
}