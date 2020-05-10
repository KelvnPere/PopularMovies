package com.fashi.popularmovies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM movies")
    LiveData<List<FavouriteEntity>> loadAllMovies();

    @Insert
    void insertMovies(FavouriteEntity favouriteEntity);

    @Delete
    void deleteMovies(FavouriteEntity favouriteEntity);
}
