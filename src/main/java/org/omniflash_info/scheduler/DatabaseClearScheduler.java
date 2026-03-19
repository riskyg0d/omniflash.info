package org.omniflash_info.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.omniflash_info.repository.NewsRepository;

import java.time.LocalDateTime;

@Component
public class DatabaseClearScheduler {

    @Autowired
    private NewsRepository newsRepository;
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional
    public void clearOldNews(){
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        newsRepository.deleteOldNews(cutoff);
        System.out.println("Cleaned up "  + " old articles.");
    }
}