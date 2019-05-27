package com.grab.news.app.repository.local;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grab.news.app.repository.models.News;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class LocalNewsDatabase extends RoomDatabase {
    
    private static final String TAG = LocalNewsDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "news_headlines";
    private static LocalNewsDatabase bookmarksDatabase;
    
    public static LocalNewsDatabase getInstance(Context context) {
        if (bookmarksDatabase == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating DB instance for storing headlines");
                bookmarksDatabase = Room.databaseBuilder(context.getApplicationContext(), LocalNewsDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(TAG, "Getting the db instance...");
        return bookmarksDatabase;
    }
    
    public abstract NewsDao newsDao();
    
}
