package com.famebuster.ui.main.fragments.feed;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.famebuster.data.Resource;
import com.famebuster.data.Status;
import com.famebuster.data.local.entities.News;
import com.famebuster.data.remote.repositories.NewsRepository;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {

    private NewsRepository newsRepository;

    @Inject
    public FeedViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;

    }

    public LiveData<Resource<List<News>>> getNews(int pageNumber){
        return newsRepository.getNewsPage(pageNumber);
    }
}
