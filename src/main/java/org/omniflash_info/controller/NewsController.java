package org.omniflash_info.controller;

import org.omniflash_info.model.News;
import org.omniflash_info.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    // Get latest news
    @GetMapping("/latest")
    public List<News> getLatestNews() {
        return newsService.getBalancedNews();
    }


    // Get news by category
    @GetMapping("/category/{category}")
    public List<News> getNewsByCategory(@PathVariable String category) {
        return newsService.getNewsByCategory(category);
    }


    // Search news
    @GetMapping("/search")
    public List<News> searchNews(@RequestParam String keyword) {
        return newsService.searchNews(keyword);
    }


    // Get news by source
    @GetMapping("/source/{source}")
    public List<News> getNewsBySource(@PathVariable String source) {
        return newsService.getNewsBySource(source);
    }


    @GetMapping("/source-time")
    public List<News> getBySourceAndTime(){
        return newsService.findAllByOrderBySourceAscPublishedAtDesc();
    }
}