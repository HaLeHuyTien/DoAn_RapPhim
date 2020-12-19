package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TaiKhoan_Adapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public TaiKhoan_Adapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= "";
        switch (position)
        {
            case 0:
                title="Thông tin";
                break;
            case 1:
                title="Giao dịch";
                break;
        }
        return title;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new TaiKhoan_ThongTinFragment();
            }
            case 1: {
                return new GiaoDichFragment();
            }
            default:
                return new TaiKhoan_ThongTinFragment();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
