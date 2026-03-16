package org.omniflash_info.scheduler;

import org.omniflash_info.reuters.RSSReuter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class NewsScheduler {

    @Autowired
    private RSSReuter rssNewsScraper;

    @Scheduled(fixedRate = 600000)
    public void scrapeNews(){

        rssNewsScraper.scrapeRSS(
                "https://www.cnbc.com/id/100003114/device/rss/rss.html",
                "CNBC");

        rssNewsScraper.scrapeRSS(
                "https://feeds.marketwatch.com/marketwatch/topstories/",
                "MarketWatch");

        rssNewsScraper.scrapeRSS(
                "https://finance.yahoo.com/news/rssindex",
                "YahooFinance");

        rssNewsScraper.scrapeRSS(
                "https://www.moneycontrol.com/rss/latestnews.xml",
                "Moneycontrol");

        System.out.println("RSS scraping completed");
    }
}