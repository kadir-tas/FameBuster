package com.famebuster.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.famebuster.data.local.entities.News;

import java.util.List;


@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNews(List<News> news);

    @Query("SELECT * FROM news WHERE pageId=:pageId")
    LiveData<List<News>> loadNews(int pageId);
}
