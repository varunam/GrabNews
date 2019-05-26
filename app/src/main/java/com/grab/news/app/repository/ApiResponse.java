package com.grab.news.app.repository;

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
}
