package org.omniflash_info.reuters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.omniflash_info.model.News;
import org.omniflash_info.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RSSReuter {

    @Autowired
    private NewsService newsService;
    @Async
    public void scrapeRSS(String url, String source){

        try{

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0 Safari/537.36")
                    .header("Accept-Language","en-US,en;q=0.9")
                    .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Referer","https://www.google.com/")
                    .timeout(8000)
                    .get();

            Elements items = doc.select("item");

            System.out.println(source + " items found: " + items.size());

            for(Element item : items){

                String title = item.select("title").text();
                String link = item.select("link").text();

                if(title == null || title.isEmpty())
                    continue;

                News news = new News();

                news.setTitle(title);
                news.setUrl(link);
                news.setSource(source);
                news.setCategory(NewsService.detectCategory(title));
                news.setPublishedTime(LocalDateTime.now());
                news.setBreaking(isBreaking(title));

                newsService.saveNews(news);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private boolean isBreaking(String title){

        String t = title.toLowerCase();

        return t.contains("breaking")
                || t.contains("alert")
                || t.contains("crash")
                || t.contains("surge")
                || t.contains("fed")
                || t.contains("rate")
                || t.contains("inflation")
                || t.contains("war");
    }
}