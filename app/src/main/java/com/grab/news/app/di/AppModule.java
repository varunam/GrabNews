package com.grab.news.app.di;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.grab.news.app.utils.Constants.BASE_URL;

/**
 * Created by varun.am on 2019-05-22
 */
@Module
public class AppModule {
    
    @Singleton
    @Provides
    RequestManager getGlide(Application application) {
        return Glide.with(application);
    }
    
    @Singleton
    @Provides
    Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    
}
