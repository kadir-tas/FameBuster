package com.famebuster.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.famebuster.di.ViewModelKey;
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

}
