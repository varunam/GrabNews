package com.grab.news.app.di;

import androidx.lifecycle.ViewModelProvider;

import com.grab.news.app.viewmodels.ViewModelProvidersFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by varun.am on 2019-05-22
 */
@Module
public abstract class ViewModelsFactoryModule {
    
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProvidersFactory viewModelProvidersFactory);
    
    
    
}
