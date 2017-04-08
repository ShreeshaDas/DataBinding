package com.example.databinding.home;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.databinding.artistdetail.ArtistDetail;
import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.model.Cast;
import com.example.databinding.model.Movie;
import com.example.databinding.model.MoviesResponse;
import com.example.databinding.network.ApiClient;
import com.example.databinding.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpRecycleView(mActivityMainBinding.movieList);
        getNowPlaying();
    }

    private void setUpRecycleView(RecyclerView recycleView) {
        mMoviesAdapter = new MoviesAdapter(this, new ArrayList<Movie>());
        recycleView.setAdapter(mMoviesAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getNowPlaying() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getNoWPlaying("4c989ba3813652e9f29d4dfd44bd34ad", 1);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                    mMoviesAdapter.setData(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }
}
