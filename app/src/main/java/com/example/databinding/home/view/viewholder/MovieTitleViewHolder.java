package com.example.databinding.home.view.viewholder;

import android.support.v7.widget.RecyclerView;

import com.example.databinding.common.BaseViewHolder;
import com.example.databinding.common.BaseViewModel;
import com.example.databinding.databinding.MovieTitleLayoutBinding;
import com.example.databinding.home.viewmodel.MovieTitleViewModel;
import com.example.databinding.model.MovieCategory;

import java.util.ArrayList;

/**
 * Created by shreesha on 10/7/17.
 */

public class MovieTitleViewHolder extends BaseViewHolder {

    private ArrayList<MovieCategory> mMovieCategories;
    private MovieTitleLayoutBinding mMovieTitleLayoutBinding;

    public MovieTitleViewHolder(MovieTitleLayoutBinding movieTitleLayoutBinding, ArrayList<MovieCategory> movieCategories) {
        super(movieTitleLayoutBinding.getRoot());
        this.mMovieCategories = movieCategories;
        this.mMovieTitleLayoutBinding = movieTitleLayoutBinding;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, BaseViewModel baseViewModel) {
        mMovieTitleLayoutBinding.setMovietitle((MovieTitleViewModel) baseViewModel);
        mMovieTitleLayoutBinding.executePendingBindings();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {

    }
}
