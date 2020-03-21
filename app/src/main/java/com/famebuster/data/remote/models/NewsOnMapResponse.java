package com.famebuster.data.remote.models;

import com.famebuster.data.local.entities.NewsOnMap;

import java.util.List;

public class NewsOnMapResponse {

    private String userIsActive;
    private List<NewsOnMap> Response;
    private String Result;

    public NewsOnMapResponse(String userIsActive, String Result, List<NewsOnMap> Response) {
        this.userIsActive = userIsActive;
        this.Result = Result;
        this.Response = Response;
    }

    public String getActive() {
        return userIsActive;
    }

    public void setActive(String active) {
        userIsActive = active;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        this.Result = result;
    }

    public List<NewsOnMap> getResponse() {
        return Response;
    }

    public void setResponse(List<NewsOnMap> response) {
        this.Response = response;
    }
}
