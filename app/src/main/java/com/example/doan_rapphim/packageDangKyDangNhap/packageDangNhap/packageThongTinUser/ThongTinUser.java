package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

public class ThongTinUser {
    private int ID;
    private String HoVaTen;
    private String Email;
    private String SDT;
    private String NgaySinh ;
    private String XaPhuong;
    private String HuyenQuan;
    private String TinhTP;
    private String MatKhau;
    private String Anh;
    private Integer TrangThai;

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Integer trangThai) {
        TrangThai = trangThai;
    }

    public void setID(int id) { this.ID = id;}
    public int getID(){return this.ID;}

    public void setHoVaTen(String hoVaTen) { this.HoVaTen = hoVaTen;}
    public String getHoVaTen(){return this.HoVaTen;}

    public void setEmail(String email) { this.Email = email;}
    public String getEmail(){return this.Email;}

    public void setSDT(String sdt) { this.SDT = sdt;}
    public String getSDT(){return this.SDT;}

    public void setXaPhuong(String xaPhuong) { this.XaPhuong = xaPhuong;}
    public String getXaPhuong(){return this.XaPhuong;}

    public void setHuyenQuan(String huyenQuan) { this.HuyenQuan = huyenQuan;}
    public String getHuyenQuan(){return this.HuyenQuan;}

    public void setTinhTP(String tinhTP) { this.TinhTP = tinhTP;}
    public String getTinhTP(){return this.TinhTP;}

    public void setNgaySinh(String ngaySinh) { this.NgaySinh = ngaySinh;}
    public String getNgaySinh(){return this.NgaySinh;}

    public String setMatKhau(String matKhau) { this.MatKhau = matKhau;
        return matKhau;
    }
    public String getMatKhau(){return this.MatKhau;}

    public void setAnh(String anh) { this.Anh = anh;}
    public String getAnh(){return this.Anh;}


}
