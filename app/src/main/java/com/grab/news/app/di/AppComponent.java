package com.grab.news.app.di;

import android.app.Application;

import com.grab.news.app.base.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by varun.am on 2019-05-22
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        Builder application(Application application);
        
        AppComponent build();
        
    }
    
}
