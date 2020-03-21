package com.famebuster.data.remote.repositories;

import androidx.lifecycle.LiveData;

import com.famebuster.data.Resource;
import com.famebuster.data.local.entities.NewsOnMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NewsOnMapRepository {

    LiveData<Resource<List<NewsOnMap>>> getNewsOnMap(Map<String, Double> latLonFields);

}
