package com.famebuster.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.famebuster.R;
import com.famebuster.databinding.ActivityMainBinding;
import com.famebuster.ui.BaseActivity;
import com.famebuster.ui.main.adapters.ViewPagerAdapter;
import com.famebuster.ui.main.fragments.feed.FeedFragment;
import com.famebuster.ui.placeholder.PlaceholderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViewPager();
    }


    private void setUpViewPager(){

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FeedFragment.newInstance());
        fragments.add(PlaceholderFragment.newInstance("TODO 2"));
        fragments.add(PlaceholderFragment.newInstance("TODO 3"));
        fragments.add(PlaceholderFragment.newInstance("TODO 4"));
        fragments.add(PlaceholderFragment.newInstance("TODO 5"));
        adapter.setFragments(fragments);
        dataBinding.viewPager.setAdapter(adapter);
        dataBinding.viewPager.addOnPageChangeListener(this);
        dataBinding.navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_home:
                if(dataBinding.viewPager.getCurrentItem() == 0){
                    Log.d(TAG, "onNavigationItemSelected: Set zero");
                    ((FeedFragment)adapter.getItem(0)).setRecyclerViewToTop();
                }
                dataBinding.viewPager.setCurrentItem(0);
                break;
            case R.id.navigation_search:
                dataBinding.viewPager.setCurrentItem(1);
                break;
            case R.id.navigation_new_post:
                dataBinding.viewPager.setCurrentItem(2);
                break;
            case R.id.navigation_like:
                dataBinding.viewPager.setCurrentItem(3);
                break;
            case R.id.navigation_profile:
                dataBinding.viewPager.setCurrentItem(4);
                break;
            default:
                Log.e(TAG, "onNavigationItemSelected: Default selected");
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        dataBinding.navigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
