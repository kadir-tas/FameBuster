package com.famebuster.data.remote.repositories;

import androidx.lifecycle.LiveData;

import com.famebuster.data.Resource;
import com.famebuster.data.local.entities.News;

import java.util.List;

public interface NewsRepository {
    LiveData<Resource<List<News>>> getNewsPage(int pageNumber);
}
