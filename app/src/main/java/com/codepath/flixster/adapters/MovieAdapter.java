package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

  Context context;
  List<Movie> movies;

  public MovieAdapter(Context context, List<Movie> movies) {
    this.context = context;
    this.movies = movies;
  }

  //usually involves inflating a layout from XML and returning the holder
  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    Log.d("MovieAdapter", "onCreateViewHolder");
    View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
    return new ViewHolder(movieView);
  }

  //involves populating data into the item through holder
  @Override
  public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    Log.d("MovieAdapter", "onBindViewHolder" + position);
    //get the movie at the passed in position
    Movie movie = movies.get(position);

    //bind the movie data into the VH
    holder.bind(movie);

  }

  //returns the total count of items of the list
  @Override
  public int getItemCount() {
    return movies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;


    public ViewHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvOverview = itemView.findViewById(R.id.tvOverview);
      ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public void bind(Movie movie) {
      tvTitle.setText(movie.getTitle());
      tvOverview.setText(movie.getOverview());

      String imageUrl;
      //if phone is in landscape, then imageUrl = backdropImage, else = posterImage
      if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        imageUrl = movie.getBackdropPath();
      }
      else {
        imageUrl = movie.getPosterPath();
      }

      Glide.with(context).load(imageUrl).into(ivPoster);
    }
  }
}
