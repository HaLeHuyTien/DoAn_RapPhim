package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doan_rapphim.R;

public class ThongTinVe extends AppCompatActivity {
private Button btnThayDoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ve);
        btnThayDoi= findViewById(R.id.btnTroVe);
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ThongTinVe.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.SoDoGhe.class);
                startActivity(intent);
            }
        });
    }
}