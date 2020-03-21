package com.famebuster.di.modules.map;

import com.famebuster.data.local.dao.NewsOnMapDao;
import com.famebuster.data.remote.repositories.NewsOnMapRepository;
import com.famebuster.data.remote.repositoriesImpl.NewsOnMapRepositoryImpl;
import com.famebuster.ui.map.MapsActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MapsModule {

    @Provides
    @Singleton
    NewsOnMapRepository provideNewsOnMapRepository(Retrofit retrofit, NewsOnMapDao newsOnMapDao){
        return new NewsOnMapRepositoryImpl(retrofit, newsOnMapDao);
    }
}
