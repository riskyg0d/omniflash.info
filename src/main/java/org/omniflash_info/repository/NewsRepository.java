package org.omniflash_info.repository;

import org.omniflash_info.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {


    List<News> findByCategory(String category);

    List<News> findBySource(String source);

    List<News> findByTitleContainingIgnoreCase(String keyword);

    List<News> findTop50ByOrderByBreakingDescPublishedTimeDesc();

    Optional<News> findByTitle(String title);

    @Modifying
    @Query("DELETE FROM News n WHERE n.publishedTime < :cutoff")
    void deleteOldNews(LocalDateTime cutoff);

    List<News> findAllByOrderByPublishedTimeDesc();
}