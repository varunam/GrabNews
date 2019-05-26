package com.grab.news.app.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.grab.news.app.R;

public class WebViewActivity extends AppCompatActivity {
    
    private static final String TAG = WebViewActivity.class.getSimpleName();
    public static final String FULL_NEWS_URL = "full_news_url";
    
    private WebView webView;
    
    private String fullNewsUrl;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        
        init();
        
        if (getIntent() != null && getIntent().hasExtra(FULL_NEWS_URL)) {
            fullNewsUrl = getIntent().getStringExtra(FULL_NEWS_URL);
            loadWebView();
        }
    }
    
    private void init() {
        enableBackOnActionBar();
        webView = findViewById(R.id.web_view_id);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setTitle("Loading...");
                setProgress(progress * 100);
                
                if (progress == 100)
                    setTitle(R.string.app_name);
            }
        });
    }
    
    private void enableBackOnActionBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    private void loadWebView() {
        webView.loadUrl(fullNewsUrl);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
