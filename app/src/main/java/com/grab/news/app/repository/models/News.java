package com.grab.news.app.repository.models;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by varun.am on 2019-05-25
 */
@Entity(tableName = "news_headlines",
        indices = {@Index(name = "author_index", value = "author", unique = true),
                @Index(name = "description_index", value = "description", unique = true)})
public class News {
    
    @PrimaryKey(autoGenerate = true)
    private int id_;
    
    @Embedded
    private Source source;
    
    private String author, title, description, url, urlToImage, publishedAt, content;
    
    @Ignore
    public News(Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }
    
    @Ignore
    public News() {
    
    }
    
    public News(int id_, Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.id_ = id_;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }
    
    public Source getSource() {
        return source;
    }
    
    public void setSource(Source source) {
        this.source = source;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrlToImage() {
        return urlToImage;
    }
    
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
    
    public String getPublishedAt() {
        return publishedAt;
    }
    
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getId_() {
        return id_;
    }
    
    public void setId_(int id_) {
        this.id_ = id_;
    }
    
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof News) {
            News news = (News) obj;
            return this.author.equals(news.getAuthor()) && this.description.equals(news.getDescription());
        }
        return false;
    }
}
