package com.example.doan_rapphim.packageDangKyDangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageThongTinUser.TabTaiKhoan;

public class DangKyActiviti extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_activiti);
        button = findViewById(R.id.btnDK1);

    }
    public void ChuyenTrang(View view){
        Intent intent = new Intent(DangKyActiviti.this, TabTaiKhoan.class);
        startActivity(intent);
    }
}

