package com.famebuster.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<VM extends ViewModel, DB extends ViewDataBinding> extends Fragment {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public VM viewModel;

    public DB dataBinding;

    public abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(getViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
