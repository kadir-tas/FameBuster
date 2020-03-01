package com.famebuster.data.local.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "news_celeb_join",
        primaryKeys = { "newsId", "celebId" },
        foreignKeys = {
                @ForeignKey(entity = News.class,
                        parentColumns = "newsId",
                        childColumns = "newsId"),
                @ForeignKey(entity = Celebrity.class,
                        parentColumns = "celebId",
                        childColumns = "celebId")
        })
public class NewsCelebJoin {
    private String newsId;
    private String celebId;
}
