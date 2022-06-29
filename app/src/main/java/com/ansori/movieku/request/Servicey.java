package com.ansori.movieku.request;

import com.ansori.movieku.utils.Credentials;
import com.ansori.movieku.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Credentials.BASE_API_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
