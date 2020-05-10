package com.fashi.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fashi.popularmovies.database.AppDatabase;
import com.fashi.popularmovies.database.FavouriteEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    public static String BASE_URL="https://api.themoviedb.org";
    public  static  int PAGE = 1;
    public static String LANGUAGE ="en-US";
    public static String movie_id ="movie_id";
    public static final String API_KEY = "c003d450fdaed7c42a785e8075f3893c";

    public TextView date, title, overview, rating;
    public ImageView img;
    Button favouriteButton, fab;
    AppDatabase mDb;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDb = AppDatabase.getsInstance(getApplicationContext());


        // Getting the Intents to populate the views
        final String dateIn = getIntent().getStringExtra("date");//getStringExtra("date");
        final String titleIn = getIntent().getStringExtra("title");
        final String overviewIn = getIntent().getStringExtra("overview");
        final int ratingIn = getIntent().getIntExtra("rating", 0);
        final int idintentIn = getIntent().getIntExtra("id", 0);
        final String imgurlIn = getIntent().getStringExtra("imgurl");

        date = findViewById(R.id.releaseDate);
        title = findViewById(R.id.movieTitle);
        overview = findViewById(R.id.movieOverview);
        rating = findViewById(R.id.voteAverage);
        img = findViewById(R.id.moviePoster);
        favouriteButton = findViewById(R.id.favourite_button);
          fab = findViewById(R.id.fab);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = idintentIn;
                String title = titleIn;
                String image = imgurlIn;
                String description = overviewIn;

                final FavouriteEntity favouriteEntity = new FavouriteEntity(id, title, image, description);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mDb.favouriteDao().insertMovies(favouriteEntity);
                    }
                });

                Toast.makeText(DetailsActivity.this, "Movie Saved", Toast.LENGTH_SHORT).show();
            }
        });

        date.setText(String.format("%s", dateIn));
        title.setText(titleIn);
        overview.setText(overviewIn);

        rating.setText("" + ratingIn);

        Glide.with(this)
                .load(imgurlIn)
                .centerCrop()
                //.fitCenter()
                .placeholder(R.drawable.ic_launcher_background)
                .into(img);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoInterface myinterface = retrofit.create(VideoInterface.class);
        Call<VideoClass> call = myinterface.getMovies(idintentIn, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<VideoClass>() {
            @Override
            public void onResponse(Call<VideoClass> call, Response<VideoClass> response) {
                VideoClass result = (VideoClass) response.body();
                List<VideoClass.ResultsBean> listofmovies = result.getResults();
                final VideoClass.ResultsBean firstmovies = listofmovies.get(1);
                // PopularMovieAdapter adapter= new PopularMovieAd);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (firstmovies.getKey() == null){
                            Toast.makeText(DetailsActivity.this,"Video not avaliable",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + firstmovies.getKey()));
                            startActivity(intent);
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<VideoClass> call, Throwable t) {

            }

        });


    }

}
