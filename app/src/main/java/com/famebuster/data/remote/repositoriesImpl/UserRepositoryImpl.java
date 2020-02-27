package com.famebuster.data.remote.repositoriesImpl;

import com.famebuster.data.remote.api.UserService;
import com.famebuster.data.remote.repositories.UserRepository;
import com.google.gson.Gson;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class UserRepositoryImpl implements UserRepository {
    private Retrofit retrofit;
    private Gson gson;

    @Inject
    public UserService userService;

    public UserRepositoryImpl(Retrofit retrofit, Gson gson) {
        this.gson = gson;
        this.retrofit = retrofit;
    }

}
