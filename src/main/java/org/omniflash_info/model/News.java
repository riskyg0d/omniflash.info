package org.omniflash_info.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String source;

    private String url;

    private String category;

    @Column(length = 2000)
    private String description;

    @Column(name="is_breaking")
    private Boolean breaking=false;

    private LocalDateTime publishedTime;

    public News() {
    }

    public News(String title, String source, String url, String category, String description, LocalDateTime publishedTime,boolean breaking) {
        this.title = title;
        this.source = source;
        this.url = url;
        this.category = category;
        this.description = description;
        this.publishedTime = publishedTime;
        this.breaking=breaking;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPublishedTime() {
        return publishedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedTime(LocalDateTime publishedTime) {
        this.publishedTime = publishedTime;
    }

    public void setBreaking(boolean breaking) {
        this.breaking = breaking;
    }
    public boolean isBreaking() {
        return breaking;
    }
}