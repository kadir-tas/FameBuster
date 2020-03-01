package com.famebuster.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.famebuster.R;
import com.famebuster.data.local.entities.News;
import com.famebuster.databinding.ItemFeedBinding;

public class FeedPagedListAdapter extends PagedListAdapter<News, FeedPagedListAdapter.FeedViewHolder> {


    protected FeedPagedListAdapter(@NonNull DiffUtil.ItemCallback<News> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding itemFeedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_feed, parent, false);
        FeedViewHolder holder = new FeedViewHolder(itemFeedBinding.getRoot(), itemFeedBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.itemFeedBinding.setNews( getItem(position) );
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {

        ItemFeedBinding itemFeedBinding;

        public FeedViewHolder(@NonNull View itemView, ItemFeedBinding itemFeedBinding) {
            super(itemView);
            this.itemFeedBinding = itemFeedBinding;
        }
    }
}
