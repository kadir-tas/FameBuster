package com.famebuster.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .build();

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Content-Type", "application/json").url(url);
        return chain.proceed(builder.build());
    }
}