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

        // Existing feeds
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

        // Newly added Indian market feeds
        rssNewsScraper.scrapeRSS(
                "https://economictimes.indiatimes.com/rssfeeds/1221656.cms",
                "Economic Times");

        rssNewsScraper.scrapeRSS(
                "https://www.thehindubusinessline.com/news/feeder/default/rss/",
                "Hindu BusinessLine");

        rssNewsScraper.scrapeRSS(
                "https://feeds.feedburner.com/ndtvnews-business",
                "NDTV Business");

        System.out.println("RSS scraping completed");
    }
}