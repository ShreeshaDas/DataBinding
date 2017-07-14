package com.example.databinding;

import android.app.Application;
import android.content.Context;

import com.example.databinding.data.factory.ApiFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by shreesha on 10/7/17.
 */

public class DataBindingApplication extends Application {
    private Scheduler mScheduler;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DataBindingApplication get(Context context) {
        return (DataBindingApplication) context.getApplicationContext();
    }

    public static DataBindingApplication getApplication(Context context) {
        return DataBindingApplication.get(context);
    }

    public Retrofit getRetrofit() {
        return ApiFactory.getClient();
    }

    public Scheduler subscribeScheduler() {
        if (mScheduler == null) {
            mScheduler = Schedulers.io();
        }
        return mScheduler;
    }
}
