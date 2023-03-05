package com.hyundai.crawler.controller;

import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.dto.CrawlingResponseDto;
import com.hyundai.crawler.service.CrawlerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "크롤링 요청 컨트롤러")
@RestController
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @Operation(summary = "크롤링 요청")
    @PostMapping("/crawling")
    public CrawlingResponseDto getCrawling(@Valid @RequestBody CrawlingRequestDto crawlingRequestDto) {
        return crawlerService.getCrawling(crawlingRequestDto);
    }

    @GetMapping("/crawling/default")
    public CrawlingResponseDto getCrawling() {
        CrawlingRequestDto crawlingRequestDto = CrawlingRequestDto.of();
        List<String> urlList = List.of("https://shop.hyundai.com/", "https://www.kia.com/", "https://www.genesis.com/");
        crawlingRequestDto.setUrlList(urlList);
        return crawlerService.getCrawling(crawlingRequestDto);
    }

    @Operation(hidden = true)
    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/swagger-ui.html");
    }
}
