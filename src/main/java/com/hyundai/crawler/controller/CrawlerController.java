package com.hyundai.crawler.controller;

import com.hyundai.crawler.dto.CrawlingRequestDto;
import com.hyundai.crawler.dto.CrawlingResponseDto;
import com.hyundai.crawler.dto.FailUrlDto;
import com.hyundai.crawler.exception.CrawlingException;
import com.hyundai.crawler.service.CrawlerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Tag(name = "크롤링 요청 컨트롤러")
@RestController
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    private final Executor crawlingExecutor;

    @Operation(summary = "크롤링 요청")
    @PostMapping("/crawling")
    public CrawlingResponseDto crawling(@Valid @RequestBody CrawlingRequestDto crawlingRequestDto) {
        //크롤링
        final List<String> urlList = crawlingRequestDto.getUrlList();
        final List<FailUrlDto> crawlingFailUrlList = new ArrayList<>();
        final List<CompletableFuture<String>> futures = new ArrayList<>(urlList.size());
        urlList.stream()
                .distinct()
                .forEach(url -> futures.add(CompletableFuture.supplyAsync(() -> crawlerService.crawlingRequest(url), crawlingExecutor)
                        .exceptionally(throwable -> {
                            if (throwable.getCause() instanceof CrawlingException crawlingException) {
                                crawlingFailUrlList.add(FailUrlDto.of(url, crawlingException.getExceptionCode().getDescription()));
                            } else {
                                crawlingFailUrlList.add(FailUrlDto.of(url));
                            }
                            return null;
                        })));

        String parseText = crawlerService.parseCrawlingData(
                futures.stream().filter(Objects::nonNull)
                        .map(CompletableFuture::join)
                        .collect(Collectors.joining())
        );

        return CrawlingResponseDto.of(parseText, crawlingFailUrlList);
    }

    @Operation(summary = "크롤링 요청(3개 사이트 default)")
    @GetMapping("/crawling/default")
    public CrawlingResponseDto crawlingDefault() {
        CrawlingRequestDto crawlingRequestDto = new CrawlingRequestDto();
        List<String> urlList = List.of("https://shop.hyundai.com", "https://www.kia.com", "https://www.genesis.com");
        crawlingRequestDto.setUrlList(urlList);
        return this.crawling(crawlingRequestDto);
    }

    @Operation(hidden = true)
    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/swagger-ui.html");
    }
}
