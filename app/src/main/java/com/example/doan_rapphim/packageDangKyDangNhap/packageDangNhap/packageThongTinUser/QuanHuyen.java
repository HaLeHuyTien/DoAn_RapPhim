package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.annotation.NonNull;

public class QuanHuyen {

    private String QuanHuyen;
    private int IDThanhPhoTinh;

    public QuanHuyen(String quanHuyen, int IDThanhPhoTinh) {
        this.QuanHuyen = quanHuyen;
        this.IDThanhPhoTinh = IDThanhPhoTinh;

    }

    public String getQuanHuyen() {
        return QuanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        QuanHuyen = quanHuyen;
    }

    public int getIDThanhPhoTinh() {
        return IDThanhPhoTinh;
    }

    public void setIDThanhPhoTinh(int IDThanhPhoTinh) {
        this.IDThanhPhoTinh = IDThanhPhoTinh;
    }

    @NonNull
    @Override
    public String toString() {
        return QuanHuyen;
    }
}
