package com.famebuster.di.modules;

import com.famebuster.ui.main.MainActivity;
import com.famebuster.ui.map.MapsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MapsActivity mapsActivity();

}
