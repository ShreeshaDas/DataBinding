package com.example.databinding.network;

/**
 * Created by shreesha on 9/2/17.
 */

import com.example.databinding.model.Cast;
import com.example.databinding.model.Movie;
import com.example.databinding.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("3/person/{personId}")
    Call<Cast> getTopRatedMovies(@Path("personId") String personId, @Query("api_key") String apiKey);

    @GET("3/movie/now_playing?language=en-US")
    Call<MoviesResponse> getNoWPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/movie/upcoming?language=en-US")
    Call<MoviesResponse> getUpcoming(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/movie/popular?language=en-US")
    Call<MoviesResponse> getPopular(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/movie/top_rated?language=en-US")
    Call<MoviesResponse> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);
}
