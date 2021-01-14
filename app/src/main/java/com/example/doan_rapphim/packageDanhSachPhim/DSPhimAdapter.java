package com.example.doan_rapphim.packageDanhSachPhim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DSPhimAdapter extends FragmentStatePagerAdapter {


    public DSPhimAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DSPhimDangChieu();
            case 1:
                return new DSPhimSapChieu();
            default:
                return new DSPhimDangChieu();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Phim Đang Chiếu";
                break;
            case 1:
                title = "Phim Sắp Chiếu";
                break;
        }
        return title;
    }
}
