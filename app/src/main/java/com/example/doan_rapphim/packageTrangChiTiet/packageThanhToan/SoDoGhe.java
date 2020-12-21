package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.content.Intent;
import android.graphics.Color;
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
     private Button btnA1;
     private Button btnA2;
     private Button btnA3;
     private Button btnA4;
     private Button btnA5;
     private Button btnB1;
     private Button btnB2;
     private Button btnB3;
     private Button btnB4;
     private Button btnB5;
     private Button btnC1;
     private Button btnC2;
     private Button btnC3;
     private Button btnC4;
     private Button btnC5;
     private Button btnD1;
     private Button btnD2;
     private Button btnD3;
     private Button btnD4;
     private Button btnD5;
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
        btnA1 = findViewById(R.id.btnA1);
        btnA2 = findViewById(R.id.btnA2);
        btnA3 = findViewById(R.id.btnA3);
        btnA4 = findViewById(R.id.btnA4);
        btnA5 = findViewById(R.id.btnA5);
        btnB1 = findViewById(R.id.btnB1);
        btnB2 = findViewById(R.id.btnB2);
        btnB3 = findViewById(R.id.btnB3);
        btnB4 = findViewById(R.id.btnB4);
        btnB5 = findViewById(R.id.btnB5);
        btnC1 = findViewById(R.id.btnC1);
        btnC2 = findViewById(R.id.btnC2);
        btnC3 = findViewById(R.id.btnC3);
        btnC4 = findViewById(R.id.btnC4);
        btnC5 = findViewById(R.id.btnC5);
        btnD1 = findViewById(R.id.btnD1);
        btnD2 = findViewById(R.id.btnD2);
        btnD3 = findViewById(R.id.btnD3);
        btnD4 = findViewById(R.id.btnD4);
        btnD5 = findViewById(R.id.btnD5);
        int ResiD = this.getResources().getIdentifier(ThongTinSoDoGhe.tenHinh,"drawable",this.getPackageName());
        imgTenHinh.setImageResource(ResiD);
        btntiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(SoDoGhe.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThanhToan.class);
            startActivity(intent);
        }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = findViewById(v.getId());
               












            }
        };
        btnA1.setOnClickListener(onClickListener);
    }



}