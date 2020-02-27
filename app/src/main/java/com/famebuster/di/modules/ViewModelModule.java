package com.famebuster.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.famebuster.util.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory userViewModelFactory);

}
