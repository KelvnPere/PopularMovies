package com.fashi.popularmovies.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class FavouriteEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String img;
    private String overView;

    public FavouriteEntity(int id, String title, String img, String overView) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.overView = overView;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getOverView() {
        return overView;
    }
}



