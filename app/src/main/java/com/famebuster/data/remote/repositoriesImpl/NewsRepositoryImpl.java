package com.famebuster.data.remote.repositoriesImpl;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.famebuster.data.Resource;
import com.famebuster.data.local.dao.NewsDao;
import com.famebuster.data.local.entities.News;
import com.famebuster.data.remote.NetworkBoundResource;
import com.famebuster.data.remote.api.NewsService;
import com.famebuster.data.remote.models.response.PagingResponse;
import com.famebuster.data.remote.repositories.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NewsRepositoryImpl implements NewsRepository {

    private static final String TAG = "NewsRepositoryImpl";

    private Retrofit retrofit;
    private NewsDao newsDao;
    private NewsService newsService;

    @Inject
    public NewsRepositoryImpl(Retrofit retrofit,NewsDao newsDao) {
        this.newsDao = newsDao;
        this.newsService = retrofit.create(NewsService.class);
    }

    @Override
    public LiveData<Resource<List<News>>> getNewsPage(int pageNumber) {
        return new NetworkBoundResource<List<News>, PagingResponse>() {

            @Override
            protected void saveCallResult(@NonNull PagingResponse item) {
                //TODO: item.getUserIsActive().equals("1") this part might not be correct I dunno
                if(item.getResult().equals("Success") && item.getUserIsActive().equals("1")){
                    Log.d(TAG, "saveCallResult: Saving results");
                    for(News n : item.getResponse())
                        n.setPageId(pageNumber);
                    newsDao.saveNews(item.getResponse());
                }else
                {
                    Log.e(TAG, "saveCallResult: " + item.getResult());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<News> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<News>> loadFromDb() {
                Log.d(TAG, "loadFromDb: Load from db");
                return newsDao.loadNews(pageNumber);
            }

            @NonNull
            @Override
            protected Call<PagingResponse> createCall() {
                return newsService.getFeedPage(pageNumber);
            }
        }.getAsLiveData();
    }

}
