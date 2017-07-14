package com.example.databinding.home.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.databinding.R;
import com.example.databinding.common.BaseViewHolder;
import com.example.databinding.databinding.MovieListLayoutBinding;
import com.example.databinding.databinding.MovieTitleLayoutBinding;
import com.example.databinding.home.view.viewholder.MovieListViewHolder;
import com.example.databinding.home.view.viewholder.MovieTitleViewHolder;
import com.example.databinding.home.viewmodel.MovieListViewModel;
import com.example.databinding.home.viewmodel.MovieTitleViewModel;
import com.example.databinding.model.MovieCategory;

import java.util.ArrayList;

/**
 * Created by shreesha on 10/7/17.
 */

public class MovieCategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<MovieCategory> mMovieCategories;
    private Context mContext;

    public MovieCategoryAdapter(Context context, ArrayList<MovieCategory> movieCategories) {
        mMovieCategories = movieCategories;
        this.mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MovieCategory.MOVIE_TITLE:
                MovieTitleLayoutBinding movieTitleLayoutBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.movie_title_layout,
                        parent,
                        false);
                return new MovieTitleViewHolder(movieTitleLayoutBinding, mMovieCategories);
            case MovieCategory.MOVIE_LIST:
                MovieListLayoutBinding movieListLayoutBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.movie_list_layout,
                        parent,
                        false);
                return new MovieListViewHolder(movieListLayoutBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof MovieTitleViewHolder) {
            holder.onBind(holder, new MovieTitleViewModel(mMovieCategories.get(position).getMediaCategory()));
        } else if (holder instanceof MovieListViewHolder) {
            holder.onBind(holder, new MovieListViewModel(mContext, mMovieCategories.get(position)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mMovieCategories.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mMovieCategories.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

}
