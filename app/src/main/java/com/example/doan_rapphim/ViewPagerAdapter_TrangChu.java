package com.example.doan_rapphim;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.DangNhapFragment;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TaiKhoan_ThongTinFragment;

public class ViewPagerAdapter_TrangChu extends FragmentStatePagerAdapter {
    public ViewPagerAdapter_TrangChu(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TrangChuFragment();
            case 1:
                return new DanhSachPhimFragment();
            case 2:
                return new timPhimFragment();
            case 3:
                if(IDUser.idUser>0)
                    return new TaiKhoan_ThongTinFragment();
                else
                    return new DangNhapFragment();

            default:
                return new TrangChuFragment();


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
