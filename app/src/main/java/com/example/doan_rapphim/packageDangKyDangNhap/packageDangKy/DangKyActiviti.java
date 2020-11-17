package com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.doan_rapphim.R;

public class DangKyActiviti extends AppCompatActivity {
    private Button button;
    private EditText HoTen;
    private ImageButton imgLichNgaySinhFormDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_activiti);
        button = findViewById(R.id.btnDangKyFormDK);
        HoTen = findViewById(R.id.txtHoTenDK);
        imgLichNgaySinhFormDangKy=findViewById(R.id.imgLichNgaySinhDK);
        imgLichNgaySinhFormDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgaySinh();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void ChonNgaySinh(){
        
    }

}

