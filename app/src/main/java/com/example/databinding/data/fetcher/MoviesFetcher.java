package com.example.databinding.data.fetcher;

import com.example.databinding.data.response.MovieApiCall;
import com.example.databinding.model.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by shreesha on 10/7/17.
 */

public class MoviesFetcher {
    public Observable<MoviesResponse> getMediaList(Retrofit retrofit, String mediaType, String type, String apiKey, int page) {
        MovieApiCall nowPlayingMovieApiCall = retrofit.create(MovieApiCall.class);
        return nowPlayingMovieApiCall.getMovies(mediaType, type, apiKey, page);
    }
}
