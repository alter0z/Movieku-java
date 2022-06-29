package com.ansori.movieku.response;

import androidx.annotation.NonNull;

import com.ansori.movieku.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    // finding the result object
    @SerializedName("results")
    @Expose
    private List<MovieModel> movie;

    public List<MovieModel> getPopularMovies() {
        return movie;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
