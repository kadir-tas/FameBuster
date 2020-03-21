package com.famebuster.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.famebuster.R;
import com.famebuster.data.local.entities.News;
import com.famebuster.databinding.ItemFeedBinding;

import java.util.ArrayList;
import java.util.List;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.FeedViewHolder>  {

    private List<News> newsList;
    private OnBindNearEnd onBindNearEnd;
    private int callBefore;
    public FeedRecyclerViewAdapter(OnBindNearEnd onBindNearEnd, int callBefore) {
        newsList = new ArrayList<>();
        this.onBindNearEnd = onBindNearEnd;
        this.callBefore = callBefore;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding itemFeedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_feed, parent, false);
        FeedViewHolder holder = new FeedViewHolder(itemFeedBinding.getRoot(), itemFeedBinding);
        return holder;
    }

    public void addNews(List<News> newsList){
        this.newsList.addAll(newsList);
    }

    public void setNews(List<News> newsList){
        this.newsList = newsList;
    }

    public List<News> getNews(){
        return  newsList;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.itemFeedBinding.setNews( newsList.get(position) );
        if(newsList.size() - position  < callBefore){
            onBindNearEnd.onBindNearEnd(position);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder{

        ItemFeedBinding itemFeedBinding;

        public FeedViewHolder(@NonNull View itemView, ItemFeedBinding itemFeedBinding) {
            super(itemView);
            this.itemFeedBinding = itemFeedBinding;
        }
    }


    public interface OnBindNearEnd{
        public void onBindNearEnd(int itemIndex);
    }
}
