package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.BinhLuanListAdapter;
import com.example.doan_rapphim.packageTrangChiTiet.BinhLuan_Json;
import com.example.doan_rapphim.packageTrangChiTiet.ReadBinhLuanJson;
import com.example.doan_rapphim.packageTrangChiTiet.ReadThongTinJson;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinJson;

public class ThanhToan extends AppCompatActivity {

    private TextView txtTT_TenPhim;
    private TextView txtTT_LuuY;
    private TextView txtTT_Ngay;
    private TextView txtTT_KhungGio;
    private TextView txtTT_Rap;
    private TextView txtTT_Ghe;
    private TextView txtTT_TTien;
    private TextView txtTT_Sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        txtTT_TenPhim = findViewById(R.id.txtTenPhim);
        txtTT_LuuY = findViewById(R.id.txtChuY);
        txtTT_Ngay = findViewById(R.id.txtNgay);
        txtTT_KhungGio = findViewById(R.id.txtGio);
        txtTT_Rap = findViewById(R.id.txtDiaDiem);
        txtTT_Ghe = findViewById(R.id.txtSoGhe);
        txtTT_TTien = findViewById(R.id.txtTien);
        txtTT_Sl = findViewById(R.id.txtSoluong);
        HiennThiDanhSach();
    }

    public void HiennThiDanhSach() {
        try {
            ThanhToanJson thanhToanJson = ReadThanhToanJson.readThanhToanJsonFile(this);
            txtTT_TenPhim.setText(thanhToanJson.getTenPhim());
           txtTT_LuuY.setText(thanhToanJson.getLuuY());
           txtTT_Ngay.setText(thanhToanJson.getNgay());
             txtTT_KhungGio.setText(thanhToanJson.getKhungGio());
          txtTT_Rap.setText(thanhToanJson.getRap());
           txtTT_Ghe.setText(thanhToanJson.getGhe());
            txtTT_Sl.setText(thanhToanJson.getSl().toString());
            txtTT_TTien.setText(thanhToanJson.getTong().toString());

        } catch (Exception e) {
            txtTT_TenPhim.setText("Loi !!");
        }


    }
}