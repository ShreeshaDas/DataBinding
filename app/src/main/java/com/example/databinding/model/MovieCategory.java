package com.example.databinding.model;

import java.util.ArrayList;

/**
 * Created by shreesha on 10/7/17.
 */

public class MovieCategory {
    public static final int MOVIE_TITLE = 0;
    public static final int MOVIE_LIST = 1;

    private String mCategoryTitle;
    private ArrayList<Movie> mMovies;
    private String mQueryType;
    private int mType;
    private String mMediaType;
    private NetworkError mNetworkError;

    public MovieCategory(String movieTitle, ArrayList<Movie> movies, int type, String queryType, String mediaType) {
        this.mCategoryTitle = movieTitle;
        this.mMovies = movies;
        this.mType = type;
        this.mQueryType = queryType;
        this.mMediaType = mediaType;
    }

    public String getMediaCategory() {
        return mCategoryTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.mCategoryTitle = movieTitle;
    }

    public ArrayList<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.mMovies = movies;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getQueryType() {
        return mQueryType;
    }

    public void setQueryType(String mQueryType) {
        this.mQueryType = mQueryType;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mMediaType) {
        this.mMediaType = mMediaType;
    }

    public NetworkError getNetworkError() {
        return mNetworkError;
    }

    public void setNetworkError(NetworkError networkError) {
        this.mNetworkError = networkError;
    }


    @Override
    public int hashCode() {
        int prime = 37;
        int result = 1;
        result = prime * result + mCategoryTitle.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        MovieCategory media = (MovieCategory) o;
        if (!this.mCategoryTitle.equals(media.getMediaCategory())) return false;
        return true;
    }
}
