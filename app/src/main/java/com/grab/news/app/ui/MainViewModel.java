package com.grab.news.app.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.grab.news.app.BuildConfig;
import com.grab.news.app.repository.ApiResponse;
import com.grab.news.app.repository.News;
import com.grab.news.app.repository.local.LocalNewsDatabase;
import com.grab.news.app.repository.remote.NewsRepository;
import com.grab.news.app.repository.remote.NewsService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        
        //loading data from remote
        loadDataFromRemote();
        
        //load data from cache to ui
        localNewsDatabase = LocalNewsDatabase.getInstance(application.getApplicationContext());
        newsList = localNewsDatabase.newsDao().loadHeadlines();
        if (newsList.getValue() != null)
            Log.d(TAG, "MainViewModel: news headlines retrieved: " + newsList.getValue().size());
    }
    
    private void loadDataFromRemote() {
        NewsRepository.getApiClient().create(NewsService.class)
                .getNews(BuildConfig.API_KEY)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponseObserver);
    }
    
    public LiveData<List<News>> getNewsList() {
        return newsList;
    }
    
    private Observer<? super ApiResponse> apiResponseObserver = new Observer<ApiResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
        
        }
        
        @Override
        public void onNext(ApiResponse apiResponse) {
            for (News news : apiResponse.newsList) {
                LocalNewsDatabase.getInstance(getApplication().getApplicationContext())
                        .newsDao()
                        .insertNews(news);
            }
        }
        
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: Error", e);
        }
        
        @Override
        public void onComplete() {
        
        }
    };
}
