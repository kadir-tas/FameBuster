package com.famebuster.data.remote.api;

import com.famebuster.data.remote.models.response.PagingResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @POST("v2/file_getFeed.php")
    Call<PagingResponse> getFeedPage(@Query("pageNumber") int pageNumber);

}
