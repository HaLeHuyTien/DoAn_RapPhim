package com.example.doan_rapphim;

public class Photo {
    private String TenHinh;
    private String TenPhim;
    private  String TenLoai;
    private  String Diem;
    private String Tuoi;

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public Photo(String tenHinh, String tenPhim, String tenLoai, String diem, String tuoi) {
        TenHinh = tenHinh;
        TenPhim = tenPhim;
        TenLoai = tenLoai;
        Diem = diem;
        Tuoi = tuoi;
    }

    public String getTuoi() {
        return Tuoi;
    }



    public void setTuoi(String tuoi) {
        Tuoi = tuoi;
    }



    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        TenPhim = tenPhim;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }




    public String getTenHinh() {
        return TenHinh;
    }

    public void setTenHinh(String tenHinh) {
        TenHinh = tenHinh;
    }
}
