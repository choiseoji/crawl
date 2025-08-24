package com.crawl.server;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlService {

    private WebDriver driver;
    private final String oliveYoungCreamUrl = "https://www.oliveyoung.co.kr/store/display/getMCategoryList.do?dispCatNo=100000100010015&isLoginCnt=1&aShowCnt=0&bShowCnt=0&cShowCnt=0&trackingCd=Cat100000100010015_MID&trackingCd=Cat100000100010015_MID&t_page=%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC%EA%B4%80&t_click=%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC%EC%83%81%EC%84%B8_%EC%A4%91%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC&t_1st_category_type=%EB%8C%80_%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&t_2nd_category_type=%EC%A4%91_%ED%81%AC%EB%A6%BC";

    public void startCrawling() {

        initDriver();
        try {
            driver.get(oliveYoungCreamUrl);

            List<String> itemLinks = getItemLinks();


        } finally {
            driver.quit();
        }
    }

    private void initDriver() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    private List<String> getItemLinks() {

        List<String> links = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // .이 클래스, 공백이 하위 요소
        // ul 바로 아래 li 만 가져오기
        List<WebElement> items = driver.findElements(By.cssSelector("ul.cate_prd_list li"));
        for (WebElement item : items) {

            WebElement linkElement = item.findElement(By.cssSelector("a"));
            String href = linkElement.getDomAttribute("href");  // href의 속성 값 가져오기
            links.add(href);
        }
        return links;
    }
}
