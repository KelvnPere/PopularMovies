package com.fashi.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fashi.popularmovies.R;
import com.fashi.popularmovies.database.FavouriteEntity;

import java.util.List;
        /* This TaskAdapter creates and binds ViewHolders, that hold the description and priority of a task,
        * to a RecyclerView to efficiently display data.
        */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.TaskViewHolder> {


    // Class variables for the List that holds task data and the Context
    private List<FavouriteEntity> favouriteEntities;
    private Context mContext;

            public FavouriteAdapter(List<FavouriteEntity> favouriteEntities, Context mContext) {
                this.favouriteEntities = favouriteEntities;
                this.mContext = mContext;
            }

            /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.image_favourite_item, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        // Determine the values of the wanted data
        FavouriteEntity favouriteEntity = favouriteEntities.get(position);

        Glide.with(mContext)
                .load(favouriteEntity.getImg())
                .fitCenter()
                .into(holder.img);
    }

    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (favouriteEntities == null) {
            return 0;
        }
        return favouriteEntities.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<FavouriteEntity> favouriteEntities) {
        this.favouriteEntities = favouriteEntities;
        notifyDataSetChanged();
    }

    // Inner class for creating ViewHolders
    class TaskViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        ImageView img;

        public TaskViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_favourite);
        }

    }
}