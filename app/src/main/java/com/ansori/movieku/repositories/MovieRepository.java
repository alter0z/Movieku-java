package com.ansori.movieku.repositories;

import androidx.lifecycle.LiveData;

import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private final MovieApiClient movieApiClient;

    private String queries;
    private int pageNumbers,pages;

    public static MovieRepository getInstance() {
        if(instance == null) {
            instance = new MovieRepository();
        }

        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return movieApiClient.getPopularMovies();
    }

    // 2 -> calling the method in repository
    public void searchMovieApi(String query,int pageNumber) {
        queries = query;
        pageNumbers = pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public void getPopularMovieApi(int page) {
        pages = page;
        movieApiClient.getPopularMovieApi(page);
    }

    // next page
    public void searchNextPage(){
        searchMovieApi(queries,pageNumbers+1);
    }

    public void popularMovieNExtPage(){
        getPopularMovieApi(pages+1);
    }
}
