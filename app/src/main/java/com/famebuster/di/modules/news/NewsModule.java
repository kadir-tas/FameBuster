package com.famebuster.di.modules.news;

import com.famebuster.data.local.dao.NewsDao;
import com.famebuster.data.remote.repositories.NewsRepository;
import com.famebuster.data.remote.repositoriesImpl.NewsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewsModule {

    @Provides
    @Singleton
    NewsRepository provideNewsRepository(Retrofit retrofit, NewsDao newsDao){
        return new NewsRepositoryImpl(retrofit, newsDao);
    }

}
