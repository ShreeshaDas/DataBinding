package com.example.databinding.artistdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.databinding.R;
import com.example.databinding.databinding.ArtistDetailBinding;
import com.example.databinding.model.Cast;
import com.example.databinding.network.ApiClient;
import com.example.databinding.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shreesha on 13/2/17.
 */

public class ArtistDetail extends AppCompatActivity {

    private static final String TAG = ArtistDetail.class.getSimpleName();
    private ArtistDetailPresenter mArtistDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtistDetailPresenter = new ArtistDetailPresenter();

        final ArtistDetailBinding artistDetailBinding = DataBindingUtil.setContentView(this, R.layout.artist_detail);
        artistDetailBinding.setCastPresenter(mArtistDetailPresenter);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Cast> call = apiService.getTopRatedMovies("52849", "4c989ba3813652e9f29d4dfd44bd34ad");
        call.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(Call<Cast> call, Response<Cast> response) {
                Log.d(TAG, response.body().getName());
                artistDetailBinding.setCast(response.body());
            }

            @Override
            public void onFailure(Call<Cast> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }
}
