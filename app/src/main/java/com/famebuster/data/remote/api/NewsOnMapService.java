package com.famebuster.data.remote.api;

import com.famebuster.data.remote.models.NewsOnMapResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NewsOnMapService {

    @POST("v2/file_newsOnMap.php")
    @FormUrlEncoded
    Call<NewsOnMapResponse> getNewsOnMap(@FieldMap Map<String, Double> latLonFields);

}
