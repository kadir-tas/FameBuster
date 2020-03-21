package com.famebuster.data;

import androidx.room.TypeConverter;

import com.famebuster.data.local.entities.Celebrity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromCelebList(List<Celebrity> celebrities) {
        if (celebrities == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Celebrity>>() {}.getType();
        String json = gson.toJson(celebrities, type);
        return json;
    }


    @TypeConverter
    public List<Celebrity> toCelebList(String celebrityString) {
        if (celebrityString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Celebrity>>() {}.getType();
        List<Celebrity> countryLangList = gson.fromJson(celebrityString, type);
        return countryLangList;
    }
}
