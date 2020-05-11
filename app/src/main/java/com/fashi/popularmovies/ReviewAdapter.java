package com.fashi.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<ReviewClass.ResultsBean> movies;
    public Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView authorview,contentview;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            // title =  view.findViewById(R.id.title);
            authorview=  view.findViewById(R.id.author);
            contentview=  view.findViewById(R.id.content);

        }
    }

    //constructor
    public ReviewAdapter(Context mContext, List<ReviewClass.ResultsBean> movies) {
        this.movies = movies;
        this.mContext = mContext;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, final int position) {

        final ReviewClass.ResultsBean moviesz = movies.get(position);
        holder.contentview.setText(moviesz.getContent());
        holder.authorview.setText(moviesz.getAuthor());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}