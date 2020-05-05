package com.fashi.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailsActivity extends AppCompatActivity {
    public TextView date,title,overview,rating;
    public ImageView img;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    }
}
