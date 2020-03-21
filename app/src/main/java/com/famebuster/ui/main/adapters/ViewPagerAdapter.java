package com.famebuster.ui.main.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments = new ArrayList<>(); /*so not get null ref*/
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public Fragment getFragment(int index) {
        if(index < fragments.size())
            return fragments.get(index);
        return null;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
