package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.annotation.NonNull;

public class PhuongXa {


    public PhuongXa(String PhuongXa, int IDThanhPhoTinh, int IDQuanHuyen) {
        this.PhuongXa = PhuongXa;
        this.IDQuanHuyen = IDQuanHuyen;
        this.IDThanhPhoTinh = IDThanhPhoTinh;
    }

    public String getPhuongXa() {
        return PhuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        PhuongXa = phuongXa;
    }

    public int getIDQuanHuyen() {
        return IDQuanHuyen;
    }

    public void setIDQuanHuyen(int IDQuanHuyen) {
        this.IDQuanHuyen = IDQuanHuyen;
    }

    public int getIDThanhPhoTinh() {
        return IDThanhPhoTinh;
    }

    public void setIDThanhPhoTinh(int IDThanhPhoTinh) {
        this.IDThanhPhoTinh = IDThanhPhoTinh;
    }


    private String PhuongXa;
    private int IDThanhPhoTinh;
    private int IDQuanHuyen;


    @NonNull
    @Override
    public String toString() {
        return PhuongXa;
    }


}
