package com.fashi.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewActivity extends AppCompatActivity {
    public static String BASE_URL="https://api.themoviedb.org";
    public  static  int PAGE = 1;
    public static String API_KEY ="c003d450fdaed7c42a785e8075f3893c";
    public static String LANGUAGE ="en-US";
    public static String movie_id ="movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        final int idintentIn = getIntent().getIntExtra("id", 0);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReviewInterface myinterface = retrofit.create(ReviewInterface.class);
        Call<ReviewClass> call = myinterface.getMovies(idintentIn,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<ReviewClass>() {
            @Override
            public void onResponse(Call<ReviewClass> call, Response<ReviewClass> response) {
                ReviewClass result = (ReviewClass) response.body();
                List<ReviewClass.ResultsBean> listofmovies = result.getResults();

                recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(),listofmovies));
            }

            @Override
            public void onFailure(Call<ReviewClass> call, Throwable t) {

            }
        });
    }
}
