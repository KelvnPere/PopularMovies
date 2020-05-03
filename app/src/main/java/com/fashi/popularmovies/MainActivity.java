package com.fashi.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    MovieAdapter movieAdapter;
    TextView textView;
    public  static  int PAGE = 1;
    public static String LANGUAGE ="en-US";
    public static String CATEGORY ="popular";
    public static String BASE_URL = "https://api.themoviedb.org/";
    public static final String API_KEY = "c003d450fdaed7c42a785e8075f3893c";
    public GridLayoutManager gridLayoutManager;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.title);

        // get the reference of RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        gridLayoutManager  = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        fetchMovies();
    }

    private void fetchMovies() {

        //Call<Movie> call = Api.apiInterface().getMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

        Call<Movie> call = Api.apiInterface().getMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie result = response.body();
                List<Movie.ResultsBean> listOfMovies = result.getResults();
                Movie.ResultsBean firstMovies = listOfMovies.get(1);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
}
