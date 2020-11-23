package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

public class ThongTinUser {
    private int ID;
    private String HoVaTen;
    private String Email;
    private String SDT;
    private String NgaySinh ;
    private String DiaChi;
    private String MatKhau;
    private String Anh;
    private int TrangThai;

    public void setID(int id) { this.ID = id;}
    public int getID(){return this.ID;}

    public void setHoVaTen(String hoVaTen) { this.HoVaTen = hoVaTen;}
    public String getHoVaTen(){return this.HoVaTen;}

    public void setEmail(String email) { this.Email = email;}
    public String getEmail(){return this.Email;}

    public void setSDT(String sdt) { this.SDT = sdt;}
    public String getSDT(){return this.SDT;}

    public void setNgaySinh(String ngaySinh) { this.NgaySinh = ngaySinh;}
    public String getNgaySinh(){return this.NgaySinh;}

    public void setDiaChi(String diaChi) { this.DiaChi = diaChi;}
    public String getDiaChi(){return this.DiaChi;}

    public void setMatKhau(String matKhau) { this.MatKhau = matKhau;}
    public String getMatKhau(){return this.MatKhau;}

    public void setAnh(String anh) { this.Anh = anh;}
    public String getAnh(){return this.Anh;}

    public void setTrangThai(int trangThai) { this.TrangThai = trangThai;}
    public int getTrangThai(){return this.TrangThai;}
}
