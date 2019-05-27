package com.grab.news.app.di.main;

import com.grab.news.app.repository.remote.NewsService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by varun.am on 2019-05-26
 */
@Module
public abstract class NewsModule {
    
    @Provides
    static NewsService providesNewsService(Retrofit retrofit){
        return retrofit.create(NewsService.class);
    }
    
}
