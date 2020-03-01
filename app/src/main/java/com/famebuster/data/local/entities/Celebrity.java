package com.famebuster.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "celebrity")
public class Celebrity {

    @PrimaryKey
    @SerializedName("celebId")
    @NonNull
    private String celebId;

    @SerializedName("celebName")
    private String celebName;

    @SerializedName("celebImageUrl")
    private String celebImageUrl;

    @NonNull
    public String getCelebId() {
        return celebId;
    }

    public void setCelebId(@NonNull String celebId) {
        this.celebId = celebId;
    }

    public String getCelebName() {
        return celebName;
    }

    public void setCelebName(String celebName) {
        this.celebName = celebName;
    }

    public String getCelebImageUrl() {
        return celebImageUrl;
    }

    public void setCelebImageUrl(String celebImageUrl) {
        this.celebImageUrl = celebImageUrl;
    }
}
