package com.famebuster.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.famebuster.data.local.entities.Celebrity;

import java.util.List;

@Dao
public interface CelebrityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCelebrity(List<Celebrity> celebrities);

    @Query("SELECT * FROM Celebrity")
    LiveData<List<Celebrity>> loadCelebrity();

    @Query("SELECT * FROM Celebrity WHERE celebId=:celebId")
    LiveData<Celebrity> getCelebrity(String celebId);
}
