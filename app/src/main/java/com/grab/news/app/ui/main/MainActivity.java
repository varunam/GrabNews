package com.grab.news.app.ui.main;

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

import com.google.android.material.snackbar.Snackbar;
import com.grab.news.app.R;
import com.grab.news.app.repository.models.News;
import com.grab.news.app.repository.models.Source;
import com.grab.news.app.repository.local.LocalNewsDatabase;
import com.grab.news.app.ui.webview.WebViewActivity;
import com.grab.news.app.ui.main.adapter.NewsAdapter;
import com.grab.news.app.utils.NetworkUtils;
import com.grab.news.app.viewmodels.ViewModelProviderFactory;

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
    ViewModelProviderFactory viewModelProvidersFactory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        //addDummyData();
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        checkInternetConnection();
    }
    
    private void checkInternetConnection() {
        if (!NetworkUtils.isNetworkAvailable(getApplicationContext())) {
            Snackbar.make(findViewById(R.id.news_rv_id), R.string.internet_not_available, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkInternetConnection();
                        }
                    }).show();
        }
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
        
        mainViewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(MainViewModel.class);
        mainViewModel.getNewsList().observe(this, newsListObserver);
    }
    
    private Observer<List<News>> newsListObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> news) {
            if (news.size() > 0)
                showLoader(false);
            Log.d(TAG, "onChanged: Observed changes in db: " + news.size());
            newsAdapter.setNewsList(news);
        }
    };
    
    private void showLoader(boolean show) {
        if (show) {
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
