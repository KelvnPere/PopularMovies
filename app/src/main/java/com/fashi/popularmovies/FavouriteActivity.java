package com.fashi.popularmovies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.fashi.popularmovies.database.AppDatabase;
import com.fashi.popularmovies.database.FavouriteEntity;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        mDb = AppDatabase.getsInstance(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewModel();
    }

    private void setupViewModel() {
        // get the reference of RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favourite_recyclerView);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getFavourites().observe(this, new Observer<List<FavouriteEntity>>() {
            @Override
            public void onChanged(@Nullable List<FavouriteEntity> favouriteEntityClasses) {
                Log.d("data", "Updating list of movies from livedata in viewmodel");
                recyclerView.setAdapter(new FavouriteAdapter(favouriteEntityClasses, getApplicationContext()));
            }
        });

        }
}
