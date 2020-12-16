package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.packageThanhToan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_rapphim.R;

public class SoDoGhe extends AppCompatActivity {
 private Button btntiepTuc;
 private TextView txtTenPhim;
 private TextView txtSuatChieu;
 private ImageView imgTenHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_do_ghe);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtSuatChieu = findViewById(R.id.txtGio);
        imgTenHinh = findViewById(R.id.imgTenHinh);
        btntiepTuc = findViewById(R.id.btnTiepTuc);
        txtTenPhim.setText(ThongTinSoDoGhe.tenPhim);
        txtSuatChieu.setText(ThongTinSoDoGhe.suatChieu);
        int ResiD = this.getResources().getIdentifier(ThongTinSoDoGhe.tenHinh,"drawable",this.getPackageName());
        imgTenHinh.setImageResource(ResiD);
        btntiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(SoDoGhe.this, ThanhToan.class);
            startActivity(intent);
        }
        });
    }

}