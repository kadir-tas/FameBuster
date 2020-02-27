package com.famebuster.di.modules;

import android.content.Context;

import com.famebuster.FameBusterApp;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(FameBusterApp fameBusterApp);

}