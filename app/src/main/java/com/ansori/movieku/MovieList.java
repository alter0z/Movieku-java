package com.ansori.movieku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ansori.movieku.adapters.MovieRecycleView;
import com.ansori.movieku.adapters.OnMovieListener;
import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.models.viewmodels.MovieListViewModel;
import com.ansori.movieku.request.Servicey;
import com.ansori.movieku.response.MovieSearchResponse;
import com.ansori.movieku.utils.Credentials;
import com.ansori.movieku.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList extends AppCompatActivity implements OnMovieListener {

    // view model
    private MovieListViewModel movieListViewModel;

    // recycleView
    private RecyclerView recyclerView;
    private MovieRecycleView movieRecycleAdapter;

    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // get id
        recyclerView = findViewById(R.id.recycleView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // search view
        setupSearchView();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        // calling the observers
        ObservingAnyChange();
        ObservingPopularMovies();

        // show poular movie
        movieListViewModel.getPopularMovieApi(1);

        // configuring recycle view
        ConfigureRecycleView();
    }

    private void ObservingPopularMovies() {
        movieListViewModel.getPopularMovies().observe(this, movieModels -> {
            // observing any data change
            if (movieModels != null){
                for (MovieModel model: movieModels){
                    // get the data
                    movieRecycleAdapter.setmMovies(movieModels);
                    Log.v("tag","on changed"+model.getTitle());
                }
            }
        });
    }

//    private void getPopularMovies(){
//        movieListViewModel.getPopularMovieApi(1);
//    }

    private void setupSearchView() {
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 4 -> calling method in main activity
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(v -> isPopular = false );
    }

    // observing any data change
    private void ObservingAnyChange() {
        movieListViewModel.getMovies().observe(this, movieModels -> {
            // observing any data change
            if (movieModels != null){
                for (MovieModel model: movieModels){
                    // get the data
                    movieRecycleAdapter.setmMovies(movieModels);
                    Log.v("tag","on changed"+model.getTitle());
                }
            }
        });
    }

    // initializing recycleView and adding data to it
    private void ConfigureRecycleView() {
        movieRecycleAdapter = new MovieRecycleView(this);
        recyclerView.setAdapter(movieRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // pagination rescycle view
        // loading next result api responses
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    // here we need to display next results on the next page of api
                    movieListViewModel.searchNextPage();
                    if(isPopular)
                        movieListViewModel.popularMovieNExtPage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        // here go to detail movie that has clicked
        Intent intent = new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movieRecycleAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String Category) {

    }
}