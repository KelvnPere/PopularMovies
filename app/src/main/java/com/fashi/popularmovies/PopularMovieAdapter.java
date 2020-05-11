package com.fashi.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fashi.popularmovies.database.FavouriteEntity;

import java.util.ArrayList;
import java.util.List;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.MyViewHolder> {

    private List<Movie.ResultsBean> mMovies ;

    public Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            // title =  view.findViewById(R.id.title);
            img=  view.findViewById(R.id.grid_image_item);
        }

    }
    //constructor
    public PopularMovieAdapter(Context mContext, List<Movie.ResultsBean> mMovies) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder  holder, final int position) {

        final Movie.ResultsBean moviess = mMovies.get(position);

        Glide.with(mContext)
                .load(moviess.getPoster_path())
                //.centerCrop()
                .fitCenter()
               // .placeholder(R.drawable.ic_launcher_background)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .crossFade()
                .into(holder.img);

        // OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailsActivity.class);
                //pass data though intent using puExtra
                i.putExtra("title", moviess.getOriginal_title());
                i.putExtra("overview",moviess.getOverview());
                i.putExtra("date", moviess.getRelease_date());
                i.putExtra("rating", moviess.getVote_count());
                i.putExtra("imgurl",moviess.getPoster_path());
                i.putExtra("id",moviess.getId());
                /*
                With Android 9, you cannot start an activity
                 from a non-activity context unless
                 you pass the intent flag FLAG_ACTIVITY_NEW_TASK.
                 That's what the Flag was added.
                 */
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mMovies.size();
    }


}

