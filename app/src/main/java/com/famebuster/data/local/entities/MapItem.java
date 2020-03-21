package com.famebuster.data.local.entities;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MapItem implements ClusterItem {
//    public final String name;
//    public final int profilePhoto;
    private final LatLng mPosition;
    private String newsMapImageUrl;
    private String newsHeader;

    public MapItem(LatLng position, String newsMapImageUrl, String newsHeader/*, String name, int pictureResource*/) {
//        this.name = name;
//        profilePhoto = pictureResource;
        this.mPosition = position;
        this.newsMapImageUrl = newsMapImageUrl;
        this.newsHeader = newsHeader;
    }

    public String getNewsMapImageUrl() {
        return newsMapImageUrl;
    }

    public void setNewsMapImageUrl(String newsMapImageUrl) {
        this.newsMapImageUrl = newsMapImageUrl;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    public LatLng getmPosition() {
        return mPosition;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}