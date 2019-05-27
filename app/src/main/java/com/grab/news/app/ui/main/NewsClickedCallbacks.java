package com.grab.news.app.ui.main;

import com.grab.news.app.repository.models.News;

/**
 * Created by varun.am on 2019-05-26
 */
public interface NewsClickedCallbacks {
    public void onNewsItemClicked(News clickedNews, int position);
}
