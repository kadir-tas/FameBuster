package com.famebuster.ui.main.fragments.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.famebuster.R;
import com.famebuster.data.Resource;
import com.famebuster.data.local.entities.News;

import com.famebuster.databinding.FragmentFeedBinding;
import com.famebuster.ui.BaseFragment;
import com.famebuster.ui.main.adapters.FeedRecyclerViewAdapter;

import java.util.List;

public class FeedFragment extends BaseFragment<FeedViewModel, FragmentFeedBinding> implements FeedRecyclerViewAdapter.OnBindNearEnd {

    private static final String TAG = "FeedFragment";

    private FeedRecyclerViewAdapter adapter = new FeedRecyclerViewAdapter(this, 7);
    private int pageNumber;
    private boolean waitForNextPage = false;

    private List<News> backupNews;
    private int backUpScrollPosition = 0;

    public static FeedFragment newInstance(){
        return new FeedFragment();
    }

    @Override
    public Class<FeedViewModel> getViewModel() {
        return FeedViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_feed;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); /*view model bind*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding.feedList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.feedList.setAdapter(adapter);
    }

    private void showProgresBar(){
        dataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        dataBinding.progressBar.setVisibility(View.GONE);
    }

    private void getNews(){
        if(!waitForNextPage){
            waitForNextPage = true;
            showProgresBar();
            viewModel.getNews(pageNumber).observe(getActivity(), new Observer<Resource<List<News>>>() {
                @Override
                public void onChanged(Resource<List<News>> listResource) {
                    switch (listResource.status){
                        case SUCCESS:
                            if(listResource.data != null && listResource.data.size() > 0){
                                viewModel.getNews(pageNumber).removeObservers(getActivity());
                                adapter.addNews( listResource.data );
                                adapter.notifyDataSetChanged();
                                pageNumber = pageNumber + 1;
                                waitForNextPage = false;
                                Log.d(TAG, "onChanged: adapter item count " + adapter.getItemCount() + " and page number is " + pageNumber + " list resource size is " + listResource.data.size());
                                hideProgressBar();
                            }
                            break;
                        case ERROR:
                            hideProgressBar();
                            waitForNextPage = false;
                            break;
                        case LOADING:
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: On Pause");

        backupNews = adapter.getNews();
        backUpScrollPosition = dataBinding.feedList.getVerticalScrollbarPosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(backupNews != null){
            Log.d(TAG, "onPause: On Resume backup");
            adapter.setNews(backupNews);
            adapter.notifyDataSetChanged();
            dataBinding.feedList.setVerticalScrollbarPosition(backUpScrollPosition);
            hideProgressBar();
        }else{
            fetchInitial();
        }
    }

    public void setRecyclerViewToTop(){
        fetchInitial();
        dataBinding.feedList.smoothScrollToPosition(0);
    }

    private void fetchInitial() {
        if(!waitForNextPage) {
            this.pageNumber = 0;
            waitForNextPage = true;
            showProgresBar();
            viewModel.getNews(0).observe(getActivity(), new Observer<Resource<List<News>>>() {
                @Override
                public void onChanged(Resource<List<News>> listResource) {
                    switch (listResource.status){
                        case SUCCESS:
                            if(listResource.data != null && listResource.data.size() > 0){
                                viewModel.getNews(0).removeObservers(getActivity());
                                adapter.setNews( listResource.data );
                                waitForNextPage = false;
                                adapter.notifyDataSetChanged();
                                pageNumber = 1;
                                hideProgressBar();
                                Log.d(TAG, "onChanged retchdata: adapter item count " + adapter.getItemCount() + " and page number is " + pageNumber + " list resource size is " + listResource.data.size());
                            }
                            break;
                        case ERROR:
                            hideProgressBar();
                            waitForNextPage = false;
                            break;
                        case LOADING:
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onBindNearEnd(int itemIndex) {
        getNews();
    }
}

