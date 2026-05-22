package org.omniflash_info.service;

import org.omniflash_info.model.News;
import org.omniflash_info.repository.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getLatestNews(){
        return newsRepository.findTop50ByOrderByBreakingDescPublishedTimeDesc();
    }

    public List<News> getNewsByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

    public List<News> findAllByOrderBySourceAscPublishedAtDesc(){return newsRepository.findTop50ByOrderByBreakingDescPublishedTimeDesc();}

    public List<News> getNewsBySource(String source) {
        return newsRepository.findBySource(source);
    }


    public List<News> searchNews(String keyword) {
        return newsRepository.findByTitleContainingIgnoreCase(keyword);
    }
        public static String detectCategory(String title) {
            if (title == null) return "general";
            String t = title.toLowerCase();

            // Score each category — highest wins
            Map<String, Integer> scores = new LinkedHashMap<>();
            scores.put("crypto", score(t, "bitcoin", "crypto", "ethereum", "blockchain", "altcoin", "defi"));
            scores.put("markets", score(t, "stock", "shares", "nasdaq", "dow", "nifty", "sensex", "bse", "nse", "fii", "dii", "smallcap", "midcap", "largecap"));
            scores.put("commodities", score(t, "crude oil", "gold price", "silver price", "natural gas", "commodity", "brent", "wti"));
            scores.put("economy", score(t, "inflation", "interest rate", "gdp", "rbi", "fiscal deficit", "monetary policy", "cpi", "wpi"));
            scores.put("technology", score(t, "artificial intelligence", "ai chip", "semiconductor", "saas", "cloud computing", "startup"));
            scores.put("business", score(t, "merger", "acquisition", "earnings", "ipo", "quarterly results", "revenue", "profit"));
            scores.put("geopolitics", score(t, "sanctions", "trade war", "tariff", "military", "nato", "treaty"));
            scores.put("regulatory", score(t, "sebi", "regulation", "compliance", "penalty", "ban"));

            return scores.entrySet().stream()
                    .filter(e -> e.getValue() > 0)
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("finance");
        }
    private static int score(String text, String... keywords) {
        int s = 0;
        for (String kw : keywords) {
            if (text.contains(kw)) s++;
        }
        return s;
    }
    public News saveNews(News news) {
        if(newsRepository.findByTitle(news.getTitle()).isEmpty()){
            newsRepository.save(news);
        }
        return news;
    }
    public List<News> getBalancedNews(){

        List<News> allNews = newsRepository.findAllByOrderByPublishedTimeDesc();

        Map<String, Queue<News>> sourceMap = new HashMap<>();

        for(News n : allNews){
            sourceMap
                    .computeIfAbsent(n.getSource(), k -> new LinkedList<>())
                    .add(n);
        }

        List<News> result = new ArrayList<>();

        boolean newsRemaining = true;

        while(newsRemaining){

            newsRemaining = false;

            for(Queue<News> queue : sourceMap.values()){

                if(!queue.isEmpty()){
                    result.add(queue.poll());
                    newsRemaining = true;
                }
            }
        }

        return result;
    }
}