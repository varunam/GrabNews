package com.grab.news.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grab.news.app.R;
import com.grab.news.app.repository.News;
import com.grab.news.app.repository.Source;
import com.grab.news.app.repository.local.LocalNewsDatabase;
import com.grab.news.app.ui.MainViewModel;
import com.grab.news.app.ui.adapter.NewsAdapter;
import com.grab.news.app.ui.callbacks.NewsClickedCallbacks;
import com.grab.news.app.viewmodels.ViewModelProvidersFactory;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NewsClickedCallbacks {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private NewsAdapter newsAdapter;
    
    //views
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    
    @Inject
    ViewModelProvidersFactory viewModelProvidersFactory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        //addDummyData();
        
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
        newsAdapter = new NewsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);
        
        progressBar = findViewById(R.id.progress_bar_id);
        showLoader(true);
        
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getNewsList().observe(this, newsListObserver);
    }
    
    private Observer<List<News>> newsListObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> news) {
            showLoader(false);
            Log.d(TAG, "onChanged: Observed changes in db");
            newsAdapter.setNewsList(news);
        }
    };
    
    private void showLoader(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        } else
            progressBar.setVisibility(View.GONE);
    }
    
    @Override
    public void onNewsItemClicked(News clickedNews, int position) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.FULL_NEWS_URL, clickedNews.getUrl());
        startActivity(intent);
    }
}
