package com.ansori.movieku.utils;

import com.ansori.movieku.response.MovieResponse;
import com.ansori.movieku.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    // search movies
    @GET("search/movie/")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key,
                                          @Query("query") String query,
                                          @Query("page") int page);

    // get popular movie
    @GET("movie/popular")
    Call<MovieSearchResponse> getPopularMovie(@Query("api_key") String key,
                                              @Query("page") int page);
}
