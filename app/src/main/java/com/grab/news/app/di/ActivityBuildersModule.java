package com.grab.news.app.di;

import com.grab.news.app.di.main.MainViewModelsModule;
import com.grab.news.app.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by varun.am on 2019-05-22
 */
@Module
public abstract class ActivityBuildersModule {
    
    @ContributesAndroidInjector(
            modules = {
                    MainViewModelsModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
    
}
