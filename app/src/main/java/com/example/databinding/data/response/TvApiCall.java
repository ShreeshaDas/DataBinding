package com.example.databinding.data.response;

import com.example.databinding.model.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shreesha on 10/7/17.
 */

public interface TvApiCall {
    @GET("3/tv/on_the_air?language=en-US")
    Observable<MoviesResponse> getOnTheAir(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/tv/latest?language=en-US")
    Observable<MoviesResponse> getLatest(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/tv/popular?language=en-US")
    Observable<MoviesResponse> getPopular(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/tv/top_rated?language=en-US")
    Observable<MoviesResponse> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/tv/airing_today?language=en-US")
    Observable<MoviesResponse> getAiringToday(@Query("api_key") String apiKey, @Query("page") int page);
}
