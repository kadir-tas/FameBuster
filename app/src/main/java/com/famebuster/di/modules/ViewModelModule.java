package com.famebuster.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.famebuster.di.ViewModelKey;
import com.famebuster.ui.main.MainViewModel;
import com.famebuster.ui.main.fragments.feed.FeedViewModel;
import com.famebuster.ui.map.MapsActivityViewModel;
import com.famebuster.util.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory userViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(MapsActivityViewModel.class)
    abstract ViewModel bindsMapsActivityViewModel(MapsActivityViewModel mapsActivityViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel.class)
    abstract ViewModel bindsFeedViewModel(FeedViewModel feedViewModel);
}
