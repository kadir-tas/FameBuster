package com.famebuster.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.famebuster.data.local.dao.NewsOnMapDao;
import com.famebuster.data.local.dao.CelebrityDao;
import com.famebuster.data.local.dao.NewsDao;
import com.famebuster.data.local.dao.UserDao;
import com.famebuster.data.local.entities.NewsOnMap;
import com.famebuster.data.local.entities.Celebrity;
import com.famebuster.data.local.entities.News;
import com.famebuster.data.local.entities.User;
import com.famebuster.util.Converters;

@Database(entities = {User.class,
        NewsOnMap.class,
        News.class,
        Celebrity.class},
        version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract NewsOnMapDao newsOnMapDao();

    public abstract CelebrityDao celebrityDao();

    public abstract NewsDao newsDao();

}