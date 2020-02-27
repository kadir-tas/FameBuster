package com.famebuster.ui;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends ViewModel, DB extends ViewDataBinding> extends DaggerAppCompatActivity {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public VM viewModel;

    public abstract Class<VM> getViewModel();

    public DB dataBinding;

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(getViewModel());
    }
}