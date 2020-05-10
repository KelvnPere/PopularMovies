package com.fashi.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class DetailsActivity extends AppCompatActivity {
    public TextView date, title, overview, rating;
    public ImageView img;
    Button favouriteButton;
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

    }
}
