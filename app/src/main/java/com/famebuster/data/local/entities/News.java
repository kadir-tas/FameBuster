package com.famebuster.data.local.entities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.famebuster.data.DataConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "news")
public class News {

    @PrimaryKey
    @SerializedName("newsId")
    @NonNull
    private String newsId;

    @SerializedName("newsAddDate")
    private String newsAddDate;

    @SerializedName("userNickName")
    private String userNickName;

    @SerializedName("userImageUrl")
    private String userImageUrl;

    @SerializedName("userFollowersCount")
    private String userFollowersCount;

    @SerializedName("userBustsCount")
    private String userBustsCount;

    @SerializedName("newsCommentCount")
    private String newsCommentCount;

    @SerializedName("newsDetail")
    private String newsDetail;

    @SerializedName("newsHeader")
    private String newsHeader;

    @SerializedName("newsAddUserId")
    private String newsAddUserId;

    @SerializedName("newsImageUrl")
    private String newsImageUrl;

    @SerializedName("newsLat")
    private String newsLat;

    @SerializedName("newsLon")
    private String newsLon;

    @SerializedName("newsPlaceId")
    private String newsPlaceId;

    @SerializedName("newsPlaceName")
    private String newsPlaceName;

    @SerializedName("newsWowCount")
    private int newsWowCount;

    @SerializedName("newsIsWatermarked")
    private String newsIsWatermarked;

    @SerializedName("newsWatermarkOpacity")
    private String newsWatermarkOpacity;

    @SerializedName("newsRebustFrom")
    private String newsRebustFrom;

    @SerializedName("newsLastComment")
    private String newsLastComment;

    @SerializedName("newsViewCount")
    private String newsViewCount;

    @SerializedName("newsIsWowed")
    private String newsIsWowed;

    @SerializedName("isRebusted")
    private String isRebusted;

    @SerializedName("newsRebustId")
    private String newsRebustId;

    @SerializedName("newsPhotoLat")
    private String newsPhotoLat;

    @SerializedName("newsPhotoLon")
    private String newsPhotoLon;

    @SerializedName("newsPhotoDate")
    private String newsPhotoDate;

    @SerializedName("newsAddDateFull")
    private String newsAddDateFull;

    @SerializedName("newsIsUnderAge")
    private String newsIsUnderAge;

    @TypeConverters(DataConverter.class)
    @SerializedName("celebList")
    private List<Celebrity> celebList;

    @SerializedName("isUserBlocked")
    private int isUserBlocked;

    @SerializedName("isUserBlockedMe")
    private int isUserBlockedMe;

    @SerializedName("newsRebustCount")
    private int newsRebustCount;

    private int pageId;

    public News() {
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @NonNull
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(@NonNull String newsId) {
        this.newsId = newsId;
    }

    public String getNewsAddDate() {
        return newsAddDate;
    }

    public void setNewsAddDate(String newsAddDate) {
        this.newsAddDate = newsAddDate;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserFollowersCount() {
        return userFollowersCount;
    }

    public void setUserFollowersCount(String userFollowersCount) {
        this.userFollowersCount = userFollowersCount;
    }

    public String getUserBustsCount() {
        return userBustsCount;
    }

    public void setUserBustsCount(String userBustsCount) {
        this.userBustsCount = userBustsCount;
    }

    public String getNewsCommentCount() {
        return newsCommentCount;
    }

    public void setNewsCommentCount(String newsCommentCount) {
        this.newsCommentCount = newsCommentCount;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    public String getNewsAddUserId() {
        return newsAddUserId;
    }

    public void setNewsAddUserId(String newsAddUserId) {
        this.newsAddUserId = newsAddUserId;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public void setNewsImageUrl(String newsImageUrl) {
        this.newsImageUrl = newsImageUrl;
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

    public int getNewsWowCount() {
        return newsWowCount;
    }

    public void setNewsWowCount(int newsWowCount) {
        this.newsWowCount = newsWowCount;
    }

    public String getNewsIsWatermarked() {
        return newsIsWatermarked;
    }

    public void setNewsIsWatermarked(String newsIsWatermarked) {
        this.newsIsWatermarked = newsIsWatermarked;
    }

    public String getNewsWatermarkOpacity() {
        return newsWatermarkOpacity;
    }

    public void setNewsWatermarkOpacity(String newsWatermarkOpacity) {
        this.newsWatermarkOpacity = newsWatermarkOpacity;
    }

    public String getNewsRebustFrom() {
        return newsRebustFrom;
    }

    public void setNewsRebustFrom(String newsRebustFrom) {
        this.newsRebustFrom = newsRebustFrom;
    }

    public String getNewsLastComment() {
        return newsLastComment;
    }

    public void setNewsLastComment(String newsLastComment) {
        this.newsLastComment = newsLastComment;
    }

    public String getNewsViewCount() {
        return newsViewCount;
    }

    public void setNewsViewCount(String newsViewCount) {
        this.newsViewCount = newsViewCount;
    }

    public String getNewsIsWowed() {
        return newsIsWowed;
    }

    public void setNewsIsWowed(String newsIsWowed) {
        this.newsIsWowed = newsIsWowed;
    }

    public String getIsRebusted() {
        return isRebusted;
    }

    public void setIsRebusted(String isRebusted) {
        this.isRebusted = isRebusted;
    }

    public String getNewsRebustId() {
        return newsRebustId;
    }

    public void setNewsRebustId(String newsRebustId) {
        this.newsRebustId = newsRebustId;
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

    public String getNewsPhotoDate() {
        return newsPhotoDate;
    }

    public void setNewsPhotoDate(String newsPhotoDate) {
        this.newsPhotoDate = newsPhotoDate;
    }

    public String getNewsAddDateFull() {
        return newsAddDateFull;
    }

    public void setNewsAddDateFull(String newsAddDateFull) {
        this.newsAddDateFull = newsAddDateFull;
    }

    public String getNewsIsUnderAge() {
        return newsIsUnderAge;
    }

    public void setNewsIsUnderAge(String newsIsUnderAge) {
        this.newsIsUnderAge = newsIsUnderAge;
    }

    public List<Celebrity> getCelebList() {
        return celebList;
    }

    public void setCelebList(List<Celebrity> celebList) {
        this.celebList = celebList;
    }

    public int getIsUserBlocked() {
        return isUserBlocked;
    }

    public void setIsUserBlocked(int isUserBlocked) {
        this.isUserBlocked = isUserBlocked;
    }

    public int getIsUserBlockedMe() {
        return isUserBlockedMe;
    }

    public void setIsUserBlockedMe(int isUserBlockedMe) {
        this.isUserBlockedMe = isUserBlockedMe;
    }

    public int getNewsRebustCount() {
        return newsRebustCount;
    }

    public void setNewsRebustCount(int newsRebustCount) {
        this.newsRebustCount = newsRebustCount;
    }

}
