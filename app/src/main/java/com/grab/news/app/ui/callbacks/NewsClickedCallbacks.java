package com.grab.news.app.ui.callbacks;

import com.grab.news.app.repository.News;

/**
 * Created by varun.am on 2019-05-26
 */
public interface NewsClickedCallbacks {
    public void onNewsItemClicked(News clickedNews, int position);
}
