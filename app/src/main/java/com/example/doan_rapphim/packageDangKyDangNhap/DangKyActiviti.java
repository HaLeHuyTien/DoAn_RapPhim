package com.example.doan_rapphim.packageDangKyDangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageThongTinUser.TabTaiKhoan;

public class DangKyActiviti extends AppCompatActivity {
    private Button button;
    private EditText HoTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_activiti);
        button = findViewById(R.id.btnDangKy);
        HoTen = findViewById(R.id.txtHoTen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

