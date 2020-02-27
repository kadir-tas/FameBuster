package com.famebuster.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.famebuster.data.local.dao.UserDao;
import com.famebuster.data.local.entities.User;
import com.famebuster.util.Converters;

@Database(entities = {User.class},
        version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}