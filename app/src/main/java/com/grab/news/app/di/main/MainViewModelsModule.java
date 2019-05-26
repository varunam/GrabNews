package com.grab.news.app.di.main;

import androidx.lifecycle.ViewModel;

import com.grab.news.app.di.ViewModelKey;
import com.grab.news.app.ui.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by varun.am on 2019-05-22
 */
@Module
public abstract class MainViewModelsModule {
    
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
    
}