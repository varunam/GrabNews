package com.grab.news.app.repository.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by varun.am on 2019-05-25
 */
public class ApiResponse {
    
    @SerializedName("status")
    public String status;
    
    @SerializedName("totalResults")
    public int articles;
    
    @SerializedName("articles")
    public List<News> newsList;
    
    public ApiResponse(){
    
    }
    
    public ApiResponse(String status, int articles, List<News> newsList) {
        this.status = status;
        this.articles = articles;
        this.newsList = newsList;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getArticles() {
        return articles;
    }
    
    public void setArticles(int articles) {
        this.articles = articles;
    }
    
    public List<News> getNewsList() {
        return newsList;
    }
    
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
