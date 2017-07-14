package com.example.databinding.home.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.home.view.adapter.MovieCategoryAdapter;
import com.example.databinding.home.viewmodel.HomeViewModel;
import com.example.databinding.model.Movie;
import com.example.databinding.model.MovieCategory;
import com.example.databinding.rxbus.RxBus;
import com.example.databinding.rxbus.events.BusEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomeActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding mActivityMainBinding;
    private HomeViewModel mHomeViewModel;
    private DrawerLayout mDrawer;
    private MovieCategoryAdapter mMovieCategoryAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    ArrayList<MovieCategory> mMovieCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mHomeViewModel = new HomeViewModel(this);
        mActivityMainBinding.setHomeView(mHomeViewModel);
        setUpToolbar(mActivityMainBinding.toolbar);
        setUpNavigationDrawer();
        setUpRecycleView(mActivityMainBinding.movieList);
        //getMovies();
        setupObserver(mHomeViewModel);
    }

    private void setUpToolbar(Toolbar toolbar) {
        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpNavigationDrawer() {
        // Set up the navigation drawer.
        mDrawer = mActivityMainBinding.drawerLayout;
        mDrawer.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        reset();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawer.closeDrawers();
                        return true;
                    }
                });
    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    private void setUpRecycleView(RecyclerView recycleView) {
        mMovieCategoryAdapter = new MovieCategoryAdapter(this, createMovieCategoryList());
        recycleView.setAdapter(mMovieCategoryAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object arg) {
    }

    private ArrayList<MovieCategory> createMovieCategoryList() {
        String[] categoryList = getResources().getStringArray(R.array.category);
        for (int i = 0; i < categoryList.length; i++) {
            String[] data = categoryList[i].split(",");
            mMovieCategories.add(new MovieCategory(data[0], null, MovieCategory.MOVIE_TITLE, data[1], data[2]));
            mMovieCategories.add(new MovieCategory(data[0], new ArrayList<Movie>(), MovieCategory.MOVIE_LIST, data[1], data[2]));
        }
        return mMovieCategories;
    }

    public void subscribe() {
        Disposable disposable = RxBus.getInstance().getBus().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof MovieCategory) {
                    //updateMovieAdapter((MovieCategory) o);
                } else if (o instanceof BusEvent && (BusEvent) o == BusEvent.NETWORK_ERROR) {

                }
            }
        });
        mCompositeDisposable.add(disposable);
    }

    private void updateMovieAdapter(MovieCategory mediaCategory) {
        int position = 0;
        for (MovieCategory movieCategory : mMovieCategories) {
            if (movieCategory.getMediaCategory().equals(mediaCategory.getMediaCategory())) {
                movieCategory.setMovies(mediaCategory.getMovies());
                position = mMovieCategories.indexOf(mediaCategory);
                break;
            }
        }
        mMovieCategoryAdapter.notifyItemChanged(position);
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
