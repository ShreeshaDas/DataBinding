package com.example.databinding.artistdetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shreesha on 13/2/17.
 */

public class ArtistDetail extends AppCompatActivity {

    private static final String TAG = ArtistDetail.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   ArtistDetailViewModel artistDetailViewModel = new ArtistDetailViewModel();
        final ArtistDetailBinding artistDetailBinding = DataBindingUtil.setContentView(this, R.layout.artist_detail);
        artistDetailBinding.setCastPresenter(artistDetailViewModel);

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
        });*/
    }
}
