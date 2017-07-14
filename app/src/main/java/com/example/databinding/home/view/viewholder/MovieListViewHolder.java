package com.example.databinding.home.view.viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.databinding.common.BaseViewHolder;
import com.example.databinding.common.BaseViewModel;
import com.example.databinding.databinding.MovieListLayoutBinding;
import com.example.databinding.home.view.adapter.MoviesAdapter;
import com.example.databinding.home.viewmodel.MovieListViewModel;
import com.example.databinding.model.Movie;
import com.example.databinding.model.MovieCategory;
import com.example.databinding.rxbus.RxBus;
import com.example.databinding.rxbus.events.BusEvent;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by shreesha on 10/7/17.
 */

public class MovieListViewHolder extends BaseViewHolder {


    private static final String TAG = MovieListViewHolder.class.getSimpleName();

    private MoviesAdapter mMoviesAdapter;
    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String mMediaCategory;
    private MovieListLayoutBinding mMovieListLayoutBinding;


    public MovieListViewHolder(MovieListLayoutBinding movieListLayoutBinding) {
        super(movieListLayoutBinding.getRoot());
        this.mContext = movieListLayoutBinding.getRoot().getContext();
        this.mMovieListLayoutBinding = movieListLayoutBinding;
        initAdapter(movieListLayoutBinding);
        subscribe();
    }


    private void initAdapter(MovieListLayoutBinding movieListLayoutBinding) {
        mMoviesAdapter = new MoviesAdapter(mContext, new ArrayList<Movie>());
        movieListLayoutBinding.movieList.setAdapter(mMoviesAdapter);
        movieListLayoutBinding.movieList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, BaseViewModel baseViewModel) {
        MovieListViewModel movieListViewModel = (MovieListViewModel) baseViewModel;
        mMovieListLayoutBinding.setMovielist(movieListViewModel);
        mMediaCategory = movieListViewModel.getCategoryTitle();
        movieListViewModel.getMoviesList(mContext, (MovieListViewModel) baseViewModel);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        RxBus.getInstance().send(BusEvent.RECYCLER_VIEW_DETACHED);
        reset();
    }

    public void subscribe() {
        Disposable disposable = RxBus.getInstance().getBus()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (o instanceof MovieCategory) {
                            updateMovieAdapter((MovieCategory) o);
                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void updateMovieAdapter(MovieCategory movieCategory) {
        if (movieCategory != null && movieCategory.getMovies() != null && movieCategory.getMovies().size() > 0) {
            if (mMediaCategory.equals(movieCategory.getMediaCategory())) {
                mMoviesAdapter.setData(movieCategory.getMovies());
            }
        }
    }

    private void unSubscribeFromObservable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        mCompositeDisposable = null;
    }

}
