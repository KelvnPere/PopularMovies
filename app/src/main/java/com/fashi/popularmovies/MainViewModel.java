package com.fashi.popularmovies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fashi.popularmovies.database.AppDatabase;
import com.fashi.popularmovies.database.FavouriteEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<FavouriteEntity>> favourites;
    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getsInstance(this.getApplication());
        favourites = database.favouriteDao().loadAllMovies();

    }

    public LiveData<List<FavouriteEntity>> getFavourites() {
        return favourites;
    }
}
