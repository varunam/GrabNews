package com.grab.news.app.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grab.news.app.BuildConfig;
import com.grab.news.app.R;
import com.grab.news.app.repository.ApiResponse;
import com.grab.news.app.repository.News;
import com.grab.news.app.repository.Source;
import com.grab.news.app.repository.local.LocalNewsDatabase;
import com.grab.news.app.repository.remote.NewsRepository;
import com.grab.news.app.repository.remote.NewsService;
import com.grab.news.app.ui.MainViewModel;
import com.grab.news.app.ui.adapter.NewsAdapter;
import com.grab.news.app.viewmodels.ViewModelProvidersFactory;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private NewsAdapter newsAdapter;
    
    //views
    private RecyclerView recyclerView;
    
    @Inject
    ViewModelProvidersFactory viewModelProvidersFactory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        
        //addDummyData();
        
        NewsRepository.getApiClient().create(NewsService.class)
                .getNews(BuildConfig.API_KEY)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Log.d(TAG, "onResponse: " + response.body().articles);
                        Log.d(TAG, "onResponse: " + response.body().newsList.size());
                        for (News news : response.body().newsList) {
                            LocalNewsDatabase.getInstance(getApplicationContext())
                                    .newsDao()
                                    .insertNews(news);
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        
    }
    
    private void addDummyData() {
        
        Log.d(TAG, "" + LocalNewsDatabase.getInstance(getApplicationContext())
                .newsDao()
                .insertNews(new News(
                        new Source(null, "name"),
                        "author",
                        "title",
                        "Description",
                        "url",
                        "https://cdn.thewire.in/wp-content/uploads/2019/05/24195922/pti-modisupporters-800x400.jpg",
                        "published",
                        "content"
                )));
        
        Log.d(TAG, "" + LocalNewsDatabase.getInstance(getApplicationContext())
                .newsDao()
                .insertNews(new News(
                        new Source(null, "name"),
                        "author",
                        "title",
                        "Description",
                        "url",
                        "https://cdn.thewire.in/wp-content/uploads/2019/05/24195922/pti-modisupporters-800x400.jpg",
                        "published",
                        "content"
                )));
    }
    
    private void init() {
        recyclerView = findViewById(R.id.news_rv_id);
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);
        
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getNewsList().observe(this, newsListObserver);
    }
    
    private Observer<List<News>> newsListObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> news) {
            Log.d(TAG, "onChanged: Observed changes in db");
            
            newsAdapter.setNewsList(news);
        }
    };
}
