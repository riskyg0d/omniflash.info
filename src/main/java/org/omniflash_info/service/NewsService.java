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
    public static String detectCategory(String title){

        if(title == null) return "general";

        String t = title.toLowerCase();

        if(t.contains("bitcoin") || t.contains("crypto") || t.contains("ethereum") || t.contains("blockchain"))
            return "crypto";

        if(t.contains("stock") || t.contains("shares") || t.contains("nasdaq") || t.contains("dow"))
            return "markets";

        if(t.contains("oil") || t.contains("gold") || t.contains("gas"))
            return "commodities";

        if(t.contains("fed") || t.contains("inflation") || t.contains("interest rate") || t.contains("gdp"))
            return "economy";

        if(t.contains("apple") || t.contains("microsoft") || t.contains("tesla") || t.contains("ai"))
            return "technology";

        if(t.contains("merger") || t.contains("acquisition") || t.contains("earnings") || t.contains("ipo"))
            return "business";

        if(t.contains("war") || t.contains("sanctions") || t.contains("china") || t.contains("russia"))
            return "geopolitics";

        return "finance";
    }

    public News saveNews(News news) {
        if(newsRepository.findByTitle(news.getTitle()).isEmpty()){
            newsRepository.save(news);
        }
        return news;
    }
    public List<News> getBalancedNews(){

        List<News> allNews = newsRepository.findAll();

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