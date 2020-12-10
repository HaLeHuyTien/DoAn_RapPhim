package com.example.doan_rapphim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public   BottomNavigationView navigationView;
    public ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = findViewById(R.id.view_pager);
        navigationView = findViewById(R.id.bottom_nav);
        setup_view_pager();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.button_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.button_list_movie:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.button_user:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.button_TimPhim:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    public void setup_view_pager(){
        ViewPagerAdapter_TrangChu viewPagerAdapter_trangChu = new ViewPagerAdapter_TrangChu(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter_trangChu);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.button_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.button_list_movie).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.button_user).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.button_TimPhim).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}