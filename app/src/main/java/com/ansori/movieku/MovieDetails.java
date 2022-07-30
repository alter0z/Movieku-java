package com.ansori.movieku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansori.movieku.adapters.MovieViewHolder;
import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.utils.Credentials;
import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    private ImageView poster;
    private TextView title,genres,overview,releaseDate,ratingText;
    private LinearLayout dotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        poster = findViewById(R.id.posterDetail);
        title = findViewById(R.id.titleDetail);
        genres = findViewById(R.id.genreDetail);
        overview = findViewById(R.id.overview);
        releaseDate = findViewById(R.id.releaseDateDetail);
        ratingText = findViewById(R.id.ratingTextDetail);
        dotsLayout = findViewById(R.id.dotsDetail);

        // get data from intent
        getMovieDetail();
    }

    private void getMovieDetail() {
        if (getIntent().hasExtra("movie")) {
            MovieModel model = getIntent().getParcelableExtra("movie");

            Glide.with(this).load(Credentials.POSTER_URL +model.getPosterPath()).into(poster);
            title.setText(model.getTitle());
            overview.setText(model.getOverview());
            releaseDate.setText(model.getReleaseDate());
            String rating = String.valueOf(model.getVoteAverage());
            ratingText.setText(rating);

            // set dos rating
            TextView[] dots;
            int dotSize = 5; // ratting dots = 5
            dots = new TextView[dotSize];
            float ratings = model.getVoteAverage();
            dotsLayout.removeAllViews();

            for (int j = 0; j < dots.length; j++) {
                dots[j] = new TextView(this);
                dots[j].setText(Html.fromHtml("&#8226;"));
                dots[j].setTextColor(this.getResources().getColor(R.color.dots_bg));
                dots[j].setTextSize(50);
                dotsLayout.addView(dots[j]);
            }

            for (float j = 1.0f; j <= 1.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.dots_half));
                }
            }

            for (float j = 2.0f; j <= 2.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                }
            }

            for (float j = 3.0f; j <= 3.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.dots_half));
                }
            }

            for (float j = 4.0f; j <= 4.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                }
            }

            for (float j = 5.0f; j <= 5.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[2].setTextColor(this.getResources().getColor(R.color.dots_half));
                }
            }

            for (float j = 6.0f; j <= 6.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[2].setTextColor(this.getResources().getColor(R.color.material_blue));
                }
            }

            for (float j = 7.0f; j <= 7.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[2].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[3].setTextColor(this.getResources().getColor(R.color.dots_half));
                }
            }

            for (float j = 8.0f; j <= 8.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[2].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[3].setTextColor(this.getResources().getColor(R.color.material_blue));
                }
            }

            for (float j = 9.0f; j <= 9.9f; j += 0.1f) {
                if (ratings == j) {
                    dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[2].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[3].setTextColor(this.getResources().getColor(R.color.material_blue));
                    dots[4].setTextColor(this.getResources().getColor(R.color.dots_half));
                }
            }

            if (ratings == 10) {
                dots[0].setTextColor(this.getResources().getColor(R.color.material_blue));
                dots[1].setTextColor(this.getResources().getColor(R.color.material_blue));
                dots[2].setTextColor(this.getResources().getColor(R.color.material_blue));
                dots[3].setTextColor(this.getResources().getColor(R.color.material_blue));
                dots[4].setTextColor(this.getResources().getColor(R.color.material_blue));
            }
        }
    }
}