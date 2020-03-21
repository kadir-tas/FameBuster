package com.famebuster.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.famebuster.data.local.entities.NewsOnMap;

import java.util.List;

@Dao
public interface NewsOnMapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNewsOnMap(List<NewsOnMap> newsOnMaps);

    @Query("SELECT * FROM newsOnMap WHERE newsLat BETWEEN :lat1 AND :lat2 AND newsLon BETWEEN :lon2 AND :lon1")
    LiveData<List<NewsOnMap>> loadNewsOnMap(Double lat1, Double lat2, Double lon1, Double lon2);

}
