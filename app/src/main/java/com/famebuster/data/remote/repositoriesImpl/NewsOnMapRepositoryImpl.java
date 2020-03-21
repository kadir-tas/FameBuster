package com.famebuster.data.remote.repositoriesImpl;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.famebuster.data.Resource;
import com.famebuster.data.local.dao.NewsOnMapDao;
import com.famebuster.data.local.entities.NewsOnMap;
import com.famebuster.data.remote.NetworkBoundResource;
import com.famebuster.data.remote.api.NewsOnMapService;
import com.famebuster.data.remote.models.NewsOnMapResponse;
import com.famebuster.data.remote.repositories.NewsOnMapRepository;
import com.famebuster.util.RateLimiter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NewsOnMapRepositoryImpl implements NewsOnMapRepository {

    private RateLimiter newsOnMapRepoRateLimit = new RateLimiter<String>(10, TimeUnit.MINUTES);

    private Retrofit retrofit;

    private NewsOnMapService newsOnMapService;

    private NewsOnMapDao newsOnMapDao;

    @Inject
    public NewsOnMapRepositoryImpl(Retrofit retrofit, NewsOnMapDao newsOnMapDao) {
        this.retrofit = retrofit;
        this.newsOnMapDao = newsOnMapDao;
        this.newsOnMapService = retrofit.create(NewsOnMapService.class);
    }


    @Override
    public LiveData<Resource<List<NewsOnMap>>> getNewsOnMap(Map<String, Double> latLonFields) {
        return new NetworkBoundResource<List<NewsOnMap>, NewsOnMapResponse>() {

            @Override
            protected void saveCallResult(@NonNull NewsOnMapResponse item) {

                if (item.getResponse() != null) {
                    newsOnMapDao.saveNewsOnMap(item.getResponse());
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<NewsOnMap> data) {
                //TODO: THIS LOOP USED FOR DEBUG
//                for(int i = 0 ; i < data.size();i++) {
//                    for(int j = i + 1; j < data.size(); j++){
//                        if(data.get(i).getNewsId() == data.get(j).getNewsId()){
//                            System.out.println("QQQ" + data.get(i).getNewsId());
//                        }
//                    }
//                }
//                for(NewsOnMap n : data){
//                    if(Double.parseDouble(n.getNewsLat()) > latLonFields.get("lat2") || Double.parseDouble(n.getNewsLat()) < latLonFields.get("lat1") || Double.parseDouble(n.getNewsLon()) > latLonFields.get("lon1") || Double.parseDouble(n.getNewsLon()) < latLonFields.get("lon2")){
//                        System.out.println("VVV  " + n.getNewsId() + "    " + n.getNewsLat() + "  " + n.getNewsLon() + "  PLat  " + n.getNewsPhotoLat() + "  PLon  " + n.getNewsPhotoLon());
//                    }
//                }
                if (data == null || data.isEmpty()) {
                    return true;
                } else {
                    for (NewsOnMap n : data) {
                        if(newsOnMapRepoRateLimit.shouldFetch(n.getNewsId())){
                            return true;
                        }
                    }
                    return false;
                }
            }

            @NonNull
            @Override
            protected LiveData<List<NewsOnMap>> loadFromDb() {
                return newsOnMapDao.loadNewsOnMap(latLonFields.get("lat1"), latLonFields.get("lat2"), latLonFields.get("lon1"), latLonFields.get("lon2"));
            }

            @NonNull
            @Override
            protected Call<NewsOnMapResponse> createCall() {
                return newsOnMapService.getNewsOnMap(latLonFields);
            }
        }.getAsLiveData();
    }
}



//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//        OptionalDouble maxLat = data.stream().map(NewsOnMap::getNewsLat).mapToDouble(Double::parseDouble).max();
//        OptionalDouble minLat = data.stream().map(NewsOnMap::getNewsLat).mapToDouble(Double::parseDouble).min();
//        OptionalDouble maxLon = data.stream().map(NewsOnMap::getNewsLon).mapToDouble(Double::parseDouble).max();
//        OptionalDouble minLon = data.stream().map(NewsOnMap::getNewsLon).mapToDouble(Double::parseDouble).min();
//
//        if (latLonFields.get("lat1") <= (minLat.isPresent() ? minLat.getAsDouble() : 0)
//        && latLonFields.get("lat2") >= (maxLat.isPresent() ? maxLat.getAsDouble() : 0)
//        && latLonFields.get("lon1") >= (maxLon.isPresent() ? maxLon.getAsDouble() : 0)
//        && latLonFields.get("lon2") <= (minLon.isPresent() ? minLon.getAsDouble() : 0)) {
//        }