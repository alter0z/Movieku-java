package com.ansori.movieku.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ansori.movieku.R;
import com.ansori.movieku.models.MovieModel;

import java.util.List;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // widgets
    TextView title,tagline,releaseDate,ratting,lang;
    ImageView poster;
    LinearLayout dotsLayout;

    // on click listener
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener = onMovieListener;

        // get id
        title = itemView.findViewById(R.id.title);
        tagline = itemView.findViewById(R.id.tagline);
        releaseDate = itemView.findViewById(R.id.releaseDate);
        ratting = itemView.findViewById(R.id.rattingText);
        poster = itemView.findViewById(R.id.poster);
        dotsLayout = itemView.findViewById(R.id.dots);
        lang = itemView.findViewById(R.id.lang);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
