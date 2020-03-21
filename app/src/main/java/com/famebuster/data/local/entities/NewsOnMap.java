package com.famebuster.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "newsOnMap",indices = {@Index(value = {"newsId"},
        unique = true)})
public class NewsOnMap {

    @PrimaryKey
    @SerializedName("newsId")
    @NonNull
    private String newsId;

    @SerializedName("newsHeader")
    private String newsHeader;

    @SerializedName("newsDetail")
    private String newsDetail;

    @SerializedName("newsImageUrl")
    private String newsImageUrl;

    @SerializedName("newsMapImageUrl")
    private String newsMapImageUrl;

    @SerializedName("newsPlaceId")
    private String newsPlaceId;

    @SerializedName("newsPlaceName")
    private String newsPlaceName;

    @SerializedName("newsCountry")
    private String newsCountry;

    @SerializedName("newsCity")
    private String newsCity;

    @SerializedName("newsLat")
    private String newsLat;

    @SerializedName("newsLon")
    private String newsLon;

    @SerializedName("newsAddDate")
    private String newsAddDate;

    @SerializedName("newsAddUserId")
    private String newsAddUserId;

    @SerializedName("newsPhotoLat")
    private String newsPhotoLat;

    @SerializedName("newsPhotoLon")
    private String newsPhotoLon;

    @SerializedName("newsPhotoAddDate")
    private String newsPhotoAddDate;

//    @SerializedName("newsTimeStamp")
//    private int newsTimeStamp;

//    @SerializedName("newsWowCount")
//    private int newsWowCount;
//
//    @SerializedName("newsCommentCount")
//    private int newsCommentCount;
//
//    @SerializedName("newsCelebList")
//    private String newsCelebList;
//
//    @SerializedName("newsIsFirst")
//    private int newsIsFirst;
//
//    @SerializedName("newsIsGlobal")
//    private int newsIsGlobal;
//
//    @SerializedName("newsReadCount")
//    private int newsReadCount;
//
//    @SerializedName("newsIsWatermarked")
//    private int newsIsWatermarked;
//
//    @SerializedName("newsWatermarkOpacity")
//    private int newsWatermarkOpacity;
//
//    @SerializedName("newsRebustFrom")
//    private String newsRebustFrom;
//
//    @SerializedName("newsRebustId")
//    private String newsRebustId;
//
//    @SerializedName("newsIsDeleted")
//    private int newsIsDeleted;
//
//    @SerializedName("newsCelebrityNames")
//    private String newsCelebrityNames;
//
//    @SerializedName("newsLastComment")
//    private String newsLastComment;
//
//    @SerializedName("newsLastCommentId")
//    private int newsLastCommentId;
//
//    @SerializedName("newsViewCount")
//    private int newsViewCount;
//
//    @SerializedName("newsPhotoDate")
//    private String newsPhotoDate;
//
//    @SerializedName("isNewstag")
//    private int isNewstag;
//
//    @SerializedName("newsCountry2")
//    private String newsCountry2;
//
//    @SerializedName("newsCountry3")
//    private String newsCountry3;
//
//    @SerializedName("newsIsUnderAge")
//    private int newsIsUnderAge;
//
//    @SerializedName("newsAddedIp")
//    private String newsAddedIp;
//
//    @SerializedName("newsIsDisabled")
//    private int newsIsDisabled;
//
//    @SerializedName("newsRebustDate")
//    private String newsRebustDate;

//    public int getNewsTimeStamp() {
//        return newsTimeStamp;
//    }
//
//    public void setNewsTimeStamp(int newsTimeStamp) {
//        this.newsTimeStamp = newsTimeStamp;
//    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public void setNewsImageUrl(String newsImageUrl) {
        this.newsImageUrl = newsImageUrl;
    }

    public String getNewsMapImageUrl() {
        return newsMapImageUrl;
    }

    public void setNewsMapImageUrl(String newsMapImageUrl) {
        this.newsMapImageUrl = newsMapImageUrl;
    }

    public String getNewsPlaceId() {
        return newsPlaceId;
    }

    public void setNewsPlaceId(String newsPlaceId) {
        this.newsPlaceId = newsPlaceId;
    }

    public String getNewsPlaceName() {
        return newsPlaceName;
    }

    public void setNewsPlaceName(String newsPlaceName) {
        this.newsPlaceName = newsPlaceName;
    }

    public String getNewsCountry() {
        return newsCountry;
    }

    public void setNewsCountry(String newsCountry) {
        this.newsCountry = newsCountry;
    }

    public String getNewsCity() {
        return newsCity;
    }

    public void setNewsCity(String newsCity) {
        this.newsCity = newsCity;
    }

    public String getNewsLat() {
        return newsLat;
    }

    public void setNewsLat(String newsLat) {
        this.newsLat = newsLat;
    }

    public String getNewsLon() {
        return newsLon;
    }

    public void setNewsLon(String newsLon) {
        this.newsLon = newsLon;
    }

    public String getNewsAddDate() {
        return newsAddDate;
    }

    public void setNewsAddDate(String newsAddDate) {
        this.newsAddDate = newsAddDate;
    }

    public String getNewsAddUserId() {
        return newsAddUserId;
    }

    public void setNewsAddUserId(String newsAddUserId) {
        this.newsAddUserId = newsAddUserId;
    }

    public String getNewsPhotoLat() {
        return newsPhotoLat;
    }

    public void setNewsPhotoLat(String newsPhotoLat) {
        this.newsPhotoLat = newsPhotoLat;
    }

    public String getNewsPhotoLon() {
        return newsPhotoLon;
    }

    public void setNewsPhotoLon(String newsPhotoLon) {
        this.newsPhotoLon = newsPhotoLon;
    }

    public String getNewsPhotoAddDate() {
        return newsPhotoAddDate;
    }

    public void setNewsPhotoAddDate(String newsPhotoAddDate) {
        this.newsPhotoAddDate = newsPhotoAddDate;
    }
}
