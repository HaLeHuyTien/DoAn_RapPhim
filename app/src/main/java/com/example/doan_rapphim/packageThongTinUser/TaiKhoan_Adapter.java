package com.example.doan_rapphim.packageThongTinUser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.doan_rapphim.GiaoDichFragment;

public class TaiKhoan_Adapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public TaiKhoan_Adapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TaiKhoan_ThongTinFragment();
            case 1:
                return new GiaoDichFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
