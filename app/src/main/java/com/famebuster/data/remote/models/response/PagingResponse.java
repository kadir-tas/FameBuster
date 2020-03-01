package com.famebuster.data.remote.models.response;

import com.famebuster.data.local.entities.News;

import java.util.List;

public class PagingResponse {
    private String Result;
    private List<News> Response;
    private String userIsActive;

    public PagingResponse(String result, List<News> response, String userIsActive) {
        this.Result = result;
        this.Response = response;
        this.userIsActive = userIsActive;
    }

    public List<News> getResponse() {
        return Response;
    }

    public void setResponse(List<News> response) {
        Response = response;
    }

    public String getResult() {
        return Result;
    }
    public String getUserIsActive() {
        return userIsActive;
    }

    public void setResult( String Result ) {
        this.Result = Result;
    }

    public void setUserIsActive( String userIsActive ) {
        this.userIsActive = userIsActive;
    }
}
