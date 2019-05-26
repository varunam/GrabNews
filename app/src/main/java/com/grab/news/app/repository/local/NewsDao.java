package com.grab.news.app.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grab.news.app.repository.News;

import java.util.List;

/**
 * Created by varun.am on 2019-05-25
 */
@Dao
public interface NewsDao {
    
    @Query("SELECT * FROM news_headlines")
    LiveData<List<News>> loadHeadlines();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNews(News news);
    
}
