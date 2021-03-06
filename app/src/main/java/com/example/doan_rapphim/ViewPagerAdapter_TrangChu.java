package com.example.doan_rapphim;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.Frame_Layout_Fragment;

public class ViewPagerAdapter_TrangChu extends FragmentStatePagerAdapter {
    public ViewPagerAdapter_TrangChu(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TrangChuFragment();
            case 1:
                return new DanhSachPhimFragment();
            case 2:
                return new timPhimFragment();
            case 3:
                return new Frame_Layout_Fragment();
            default:
                return new TrangChuFragment();


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
