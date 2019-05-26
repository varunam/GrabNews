package com.grab.news.app.repository.remote;

import com.grab.news.app.repository.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by varun.am on 2019-05-25
 */
public interface NewsService {
    
    @GET("top-headlines?country=in")
    Call<ApiResponse> getNews(
            @Query("apiKey") String apiKey
    );
    
}