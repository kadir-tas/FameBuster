package com.famebuster.data.remote.api;

import com.famebuster.data.local.entities.User;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("user/{id}")
    Flowable<User> getUserData(@Header("Authorization") String token, @Path("id") int id);

}
