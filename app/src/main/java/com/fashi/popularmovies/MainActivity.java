package com.fashi.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public  static  int PAGE = 1;
    public static String LANGUAGE ="en-US";
    public static String CATEGORY ="popular";
    public static String MOST_POPULAR = "popular";
    public static String HIGHEST_RATED = "top_rated";
    public static String BASE_URL = "https://api.themoviedb.org/";
    public static final String API_KEY = "c003d450fdaed7c42a785e8075f3893c"; // When submitting this project, make sure you remove your API KEY
    public GridLayoutManager gridLayoutManager;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.title);

        // get the reference of RecyclerView
         recyclerView = findViewById(R.id.recyclerView);
        fetchMovies();
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        gridLayoutManager  = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    // Network call
    private void fetchMovies() {
        Call<Movie> call = Api.apiInterface().getMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie result = (Movie) response.body();
                List<Movie.ResultsBean> listofmovies = result.getResults();
                Movie.ResultsBean firstmovies = listofmovies.get(1);

               recyclerView.setAdapter(new PopularMovieAdapter(getApplicationContext(),listofmovies));
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred, try again" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.most_popular){
            CATEGORY = MOST_POPULAR;
            fetchMovies();
            return true;
        }
        if (id == R.id.highest_rated){
            CATEGORY = HIGHEST_RATED;
            fetchMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
