package com.ansori.movieku.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    // 3 -> calling the method in view model
    public void searchMovieApi(String query,int pageNumber) {
        movieRepository.searchMovieApi(query,pageNumber);
    }

    public void getPopularMovieApi(int page) {
        movieRepository.getPopularMovieApi(page);
    }

    // next page
    public void searchNextPage(){
        movieRepository.searchNextPage();
    }

    public void popularMovieNExtPage(){
        movieRepository.popularMovieNExtPage();
    }
}
