package com.example.databinding.model;

import android.databinding.BaseObservable;

/**
 * Created by shreesha on 14/2/17.
 */

public class MovieViewModel extends BaseObservable {
    private Movie mMovie;

    public MovieViewModel(Movie movie) {
        this.mMovie = movie;
    }

    public String getTitle() {
        return mMovie.title;
    }

    public void setMovieViewModel(Movie movie) {
        this.mMovie = movie;
        notifyChange();
    }
}
