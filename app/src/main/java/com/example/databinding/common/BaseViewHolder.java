package com.example.databinding.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.databinding.model.MovieCategory;

/**
 * Created by shreesha on 11/7/17.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(final RecyclerView.ViewHolder holder, BaseViewModel baseViewModel);

    public abstract void onDetachedFromRecyclerView(RecyclerView recyclerView);
}
