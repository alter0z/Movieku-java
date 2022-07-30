package com.ansori.movieku.adapters;

import android.text.Html;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ansori.movieku.R;
import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.utils.Credentials;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private final OnMovieListener onMovieListener;

    public MovieRecycleView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view_model,parent,false);
        return new MovieViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        // get ratting text
        String rattingText =String.valueOf(mMovies.get(i).getVoteAverage());
        TextView[] dots;
        int dotSize = 5; // ratting dots = 5

        ((MovieViewHolder)holder).title.setText(mMovies.get(i).getTitle());
        ((MovieViewHolder)holder).tagline.setText(mMovies.get(i).getTagline());
        ((MovieViewHolder)holder).releaseDate.setText(mMovies.get(i).getReleaseDate());
        ((MovieViewHolder)holder).lang.setText(mMovies.get(i).getOriginalLanguage());
        ((MovieViewHolder)holder).ratting.setText(rattingText);

        // image view using glide
        Glide.with(holder.itemView.getContext()).load(Credentials.POSTER_URL +mMovies.get(i).getPosterPath()).into(((MovieViewHolder)holder).poster);

        // calculate condition for rat bar
        float ratting = mMovies.get(i).getVoteAverage();
        dots = new TextView[dotSize];
        ((MovieViewHolder)holder).dotsLayout.removeAllViews();
        for (int j = 0; j < dots.length; j++) {
            dots[j] = new TextView(holder.itemView.getContext());
            dots[j].setText(Html.fromHtml("&#8226;"));
            dots[j].setTextColor(holder.itemView.getResources().getColor(R.color.dots_bg));
            dots[j].setTextSize(50);
            ((MovieViewHolder)holder).dotsLayout.addView(dots[j]);
        }

//        if (ratting == 1.0f || ratting == 1.1f || ratting == 1.2f || ratting == 1.3f || ratting == 1.4f || ratting == 1.5f || ratting == 1.6f || ratting == 1.7f || ratting == 1.8f || ratting == 1.9f) {
//            dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.dotsRattingHalf));
//        }

        for (float j = 1.0f; j <= 1.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.dots_half));
            }
        }

        for (float j = 2.0f; j <= 2.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
            }
        }

        for (float j = 3.0f; j <= 3.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.dots_half));
                break;
            }
        }

        for (float j = 4.0f; j <= 4.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                break;
            }
        }

        for (float j = 5.0f; j <= 5.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.dots_half));
                break;
            }
        }

        for (float j = 6.0f; j <= 6.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                break;
            }
        }

        for (float j = 7.0f; j <= 7.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[3].setTextColor(holder.itemView.getResources().getColor(R.color.dots_half));
                break;
            }
        }

        for (float j = 8.0f; j <= 8.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[3].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                break;
            }
        }

        for (float j = 9.0f; j <= 9.9f; j += 0.1f) {
            if (ratting == j) {
                System.out.println(ratting);
                dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[3].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
                dots[4].setTextColor(holder.itemView.getResources().getColor(R.color.dots_half));
                break;
            }
        }

        if (ratting == 10) {
            dots[0].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
            dots[1].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
            dots[2].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
            dots[3].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
            dots[4].setTextColor(holder.itemView.getResources().getColor(R.color.material_blue));
        }
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        } return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    public MovieModel getSelectedMovie(int pos){
        if (mMovies != null) {
            if (mMovies.size() > 0) {
                return mMovies.get(pos);
            }
        } return null;
    }
}
