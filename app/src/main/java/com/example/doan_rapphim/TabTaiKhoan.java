package com.example.doan_rapphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabTaiKhoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_tai_khoan);
        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabThongTin = findViewById(R.id.tabThongTin);
        TabItem tabGiaoDich = findViewById(R.id.tabGiaoDich);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        TaiKhoan_Adapter pagerAdapter = new TaiKhoan_Adapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void ThayDoiThongTin(View view) {
        Intent intent = new Intent(TabTaiKhoan.this,ThayDoiThongTin.class);
        startActivity(intent);
    }

    public void ThayDoiMatKhau(View view) {
        Intent intent = new Intent(TabTaiKhoan.this,ThayDoiMatKhau.class);
        startActivity(intent);
    }
    }
