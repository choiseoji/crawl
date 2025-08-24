package com.crawl.server;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlController {

    private final CrawlService crawlService;

    @PostMapping("/crawl")
    public void startCrawl() {

        crawlService.startCrawling();
    }
}
