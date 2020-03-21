package com.famebuster.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

@Entity(tableName = "users")
public class User implements ClusterItem {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("is_login")
    private boolean isLogin;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public LatLng getPosition() {
        return null;
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
