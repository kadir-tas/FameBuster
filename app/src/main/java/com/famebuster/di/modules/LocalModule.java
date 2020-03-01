package com.famebuster.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.famebuster.data.local.AppDatabase;
import com.famebuster.data.local.dao.NewsDao;
import com.famebuster.data.local.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    UserDao provideUserPlantDao(AppDatabase appDatabase){ return appDatabase.userDao(); }

    @Provides
    @Singleton
    NewsDao provideNewsDao(AppDatabase appDatabase){ return appDatabase.newsDao(); }

}
