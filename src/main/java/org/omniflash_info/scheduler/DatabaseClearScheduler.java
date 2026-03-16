package org.omniflash_info.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.omniflash_info.repository.NewsRepository;

import java.time.LocalDateTime;

@Component
public class DatabaseClearScheduler {

    @Autowired
    private NewsRepository newsRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void clearOldNews(){

        LocalDateTime cutoff = LocalDateTime.now().minusDays(1);

        newsRepository.deleteOldNews(cutoff);
    }
}