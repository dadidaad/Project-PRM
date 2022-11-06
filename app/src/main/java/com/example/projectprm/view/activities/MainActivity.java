package com.example.projectprm.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projectprm.R;
import com.example.projectprm.utils.CartHelper;
import com.example.projectprm.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    static Button cartCount;
    static int mCartCount = 0;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    CartHelper cartHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartHelper = new CartHelper(getApplication());
        mViewPager = findViewById(R.id.viewPager);
        mBottomNavigationView = findViewById(R.id.bottonNav);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.homeFragment).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.cartFragment).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.notiFragment).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.profileFragment).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.getOrCreateBadge(R.id.cartFragment).setNumber(cartHelper.getTotalQuantity());
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.cartFragment:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.notiFragment:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.profileFragment:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    public void updateBadge(int totalQuantity){
        mBottomNavigationView.getOrCreateBadge(R.id.cartFragment).setNumber(totalQuantity);
    }
}