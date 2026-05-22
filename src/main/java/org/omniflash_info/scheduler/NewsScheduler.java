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
                "https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10000664",
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

        rssNewsScraper.scrapeRSS(
                "https://www.etnownews.com/feeds/gns-etn-markets.xml",
                "Economic Times");

        rssNewsScraper.scrapeRSS(
                "https://www.etnownews.com/feeds/gns-etn-technology.xml",
                "Economic Times Technology");

        rssNewsScraper.scrapeRSS(
                "https://www.etnownews.com/feeds/gns-etn-mutual-funds.xml",
                "Economic Times Technology");


        rssNewsScraper.scrapeRSS(
                "https://www.thehindubusinessline.com/markets/feeder/default.rss",
                "Hindu BusinessLine");

        rssNewsScraper.scrapeRSS(
                "https://feeds.feedburner.com/ndtvprofit-latest",
                "NDTV Business");

        System.out.println("RSS scraping completed");
    }
}