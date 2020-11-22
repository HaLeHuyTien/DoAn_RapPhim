package com.example.doan_rapphim.packageTrangChiTiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.doan_rapphim.DanhSachPhimFragment;
import com.example.doan_rapphim.MainActivity;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.ViewPagerAdapter_TrangChiTiet;
import com.google.android.material.tabs.TabLayout;



public class TrangChiTiet extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private  Integer idPhim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chi_tiet);



        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerTrangChiTiet);

        sendDataToFragment();

        getTabs();


    }

    public void sendDataToFragment(){
        idPhim = IDPhim.ID;
    }

    public  Integer getIdPhim(){
        return idPhim;
    }

    public void setIdPhim(Integer data) {
        idPhim = data;
    }

    public void getTabs(){


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ViewPagerAdapter_TrangChiTiet viewPagerAdapter = new ViewPagerAdapter_TrangChiTiet(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                viewPagerAdapter.addFragment(LichChieuFragment.getInstance(),"Lịch chiếu");
                viewPagerAdapter.addFragment(ThongTinFragment.getInstance(),"Thông tin");
                viewPagerAdapter.addFragment(BinhLuanFragment.getInstance(),"Bình luận");
                viewPager.setAdapter(viewPagerAdapter);

                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}