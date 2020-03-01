package com.famebuster.di.modules;

import com.famebuster.ui.main.fragments.feed.FeedFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract FeedFragment feedFragment();
}
