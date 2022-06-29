package com.ansori.movieku.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ansori.movieku.AppExecutors;
import com.ansori.movieku.models.MovieModel;
import com.ansori.movieku.response.MovieResponse;
import com.ansori.movieku.response.MovieSearchResponse;
import com.ansori.movieku.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    // live data
    private MutableLiveData<List<MovieModel>> mutableLiveData;
    private MutableLiveData<List<MovieModel>> mutableLiveDataPupularMovies;

    private static MovieApiClient instance;

    // making global runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private RetrieveMoviesRunnablePopularMovie retrieveMoviesRunnablePopularMovies;

    public static MovieApiClient getInstance() {
        if(instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mutableLiveData = new MutableLiveData<>();
        mutableLiveDataPupularMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mutableLiveData;
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return mutableLiveDataPupularMovies;
    }

    // 1 -> method to call trough classes
    public void searchMovieApi(String query,int pageNumber) {

        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query,pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            // cancelling the retrofit call
            myHandler.cancel(true);
        },3000, TimeUnit.MILLISECONDS);
    }

    public void getPopularMovieApi(int page) {

        if(retrieveMoviesRunnablePopularMovies != null){
            retrieveMoviesRunnablePopularMovies = null;
        }

        retrieveMoviesRunnablePopularMovies = new RetrieveMoviesRunnablePopularMovie(page);

        final Future myHandlers = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnablePopularMovies);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            // cancelling the retrofit call
            myHandlers.cancel(true);
        },3000, TimeUnit.MILLISECONDS);
    }


    // retrieving data from REST API by runnable class
    private class RetrieveMoviesRunnable implements Runnable {

        private final String query;
        private final int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            // getting the response object
            try {
                Response response = getMovies(query,pageNumber).execute();

                if(cancelRequest){
                    return;
                }

                if(response.code() == 200){
                    assert response.body() != null;
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());

                    if(pageNumber == 1){
                        // sending data to live data
                        // postValue -> used for background thread
                        // setValue -> not for background thread
                        mutableLiveData.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mutableLiveData.getValue();
                        assert currentMovies != null;
                        currentMovies.addAll(list);
                        mutableLiveData.postValue(currentMovies);
                    }
                } else {
                    assert response.errorBody() != null;
                    String error = response.errorBody().string();
                    Log.v("tag","Error"+error);
                    mutableLiveData.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mutableLiveData.postValue(null);
            }
        }

        // search method
        private Call<MovieSearchResponse> getMovies(String query,int pageNumber) {
            return Servicey.getMovieApi().searchMovie(Credentials.API_KEY,query,pageNumber);
        }

        private void cancelRequest() {
            Log.v("tag","Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnablePopularMovie implements Runnable {

        private final int page;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePopularMovie(int page) {
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {

            // getting the response object
            try {
                Response popularMovieResponses = getPopularMovies(page).execute();

                if(cancelRequest){
                    return;
                }

                if(popularMovieResponses.code() == 200){
                    assert popularMovieResponses.body() != null;
                    List<MovieModel> popularList = new ArrayList<>(((MovieSearchResponse)popularMovieResponses.body()).getMovies());

                    if(page == 1){
                        // sending data to live data
                        // postValue -> used for background thread
                        // setValue -> not for background thread
                        mutableLiveDataPupularMovies.postValue(popularList);
                    } else {
                        List<MovieModel> currentMovies = mutableLiveDataPupularMovies.getValue();
                        assert currentMovies != null;
                        currentMovies.addAll(popularList);
                        mutableLiveDataPupularMovies.postValue(currentMovies);
                    }
                } else {
                    assert popularMovieResponses.errorBody() != null;
                    String error = popularMovieResponses.errorBody().string();
                    Log.v("tag","Error"+error);
                    mutableLiveDataPupularMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mutableLiveDataPupularMovies.postValue(null);
            }
        }

        // search method
        private Call<MovieSearchResponse> getPopularMovies(int page){
            return Servicey.getMovieApi().getPopularMovie(Credentials.API_KEY,page);
        }

        private void cancelRequest() {
            Log.v("tag","Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
