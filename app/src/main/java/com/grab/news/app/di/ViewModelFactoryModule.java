package com.grab.news.app.di;

import androidx.lifecycle.ViewModelProvider;

import com.grab.news.app.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by varun.am on 2019-05-27
 */
@Module
public abstract class ViewModelFactoryModule {
    
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
    
}
