package com.example.databinding.data.response;

import com.example.databinding.model.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shreesha on 10/7/17.
 */

public interface MovieApiCall {
    @GET("3/{media}/{type}?language=en-US")
    Observable<MoviesResponse> getMovies(@Path("media") String mediaType, @Path("type") String type, @Query("api_key") String apiKey, @Query("page") int page);
}
