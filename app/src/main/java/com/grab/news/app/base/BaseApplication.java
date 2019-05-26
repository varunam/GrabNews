package com.grab.news.app.base;


import com.grab.news.app.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by varun.am on 2019-05-22
 */
public class BaseApplication extends DaggerApplication {
    
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
