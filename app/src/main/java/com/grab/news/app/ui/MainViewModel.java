package com.grab.news.app.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.grab.news.app.repository.News;
import com.grab.news.app.repository.local.LocalNewsDatabase;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by varun.am on 2019-05-22
 */
public class MainViewModel extends AndroidViewModel {
    
    private static final String TAG = MainViewModel.class.getSimpleName();
    
    private LocalNewsDatabase localNewsDatabase;
    private LiveData<List<News>> newsList;
    
    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "MainViewModel: created");
        localNewsDatabase = LocalNewsDatabase.getInstance(application.getApplicationContext());
        newsList = localNewsDatabase.newsDao().loadHeadlines();
        if (newsList.getValue() != null)
            Log.d(TAG, "MainViewModel: news headlines retrieved: " + newsList.getValue().size());
    }
    
    public LiveData<List<News>> getNewsList() {
        return newsList;
    }
}
