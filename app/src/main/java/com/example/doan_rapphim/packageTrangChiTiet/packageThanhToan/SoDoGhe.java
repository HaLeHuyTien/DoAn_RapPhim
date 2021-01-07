package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load("http://0306181355.cenpixelt.com/rapphim/public/images/" + ThongTinSoDoGhe.tenHinh).into(imgTenHinh);
        btntiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IDUser.idUser < 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SoDoGhe.this);
                    // Set a title for alert dialog
                    builder.setTitle("Thông Báo");

                    // Ask the final question
                    builder.setMessage("Vui lòng đăng nhập để tiếp tục!!!");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            // Set the TextView visibility GONE
                        }
                    });
                    // Set the alert dialog no button click listener


                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                } else {
                    ThongTinSoDoGhe.IDKhachHang = IDUser.idUser;
                    Intent intent = new Intent(SoDoGhe.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThanhToan.class);
                    startActivity(intent);
                }
            }
        });

        ThongTinSoDoGhe.sl = 0;
        ThongTinSoDoGhe.tongTien = 0;
        for (int i = 0; i < 4; i++) {
            for (Integer j = 0; j < 5; j++) {
                ThongTinSoDoGhe.Ghe[i][j] = false;
            }
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = findViewById(v.getId());
                String Hang = getResources().getResourceEntryName(v.getId()).substring(3, 4);
                Integer Cot = Integer.parseInt(getResources().getResourceEntryName(v.getId()).substring(4));
                if (btn.isSelected() == true) {
                    ThongTinSoDoGhe.sl--;
                    btn.setSelected(false);
                    if (Hang.equals("A")) {
                        ThongTinSoDoGhe.Ghe[0][Cot - 1] = false;
                    } else if (Hang.equals("B")) {
                        ThongTinSoDoGhe.Ghe[1][Cot - 1] = false;
                    } else if (Hang.equals("C")) {
                        ThongTinSoDoGhe.Ghe[2][Cot - 1] = false;
                    } else if (Hang.equals("D")) {
                        ThongTinSoDoGhe.Ghe[3][Cot - 1] = false;
                    }


                } else {
                    ThongTinSoDoGhe.sl++;
                    btn.setSelected(true);
                    if (Hang.equals("A")) {
                        ThongTinSoDoGhe.Ghe[0][Cot - 1] = true;
                    } else if (Hang.equals("B")) {
                        ThongTinSoDoGhe.Ghe[1][Cot - 1] = true;
                    } else if (Hang.equals("C")) {
                        ThongTinSoDoGhe.Ghe[2][Cot - 1] = true;
                    } else if (Hang.equals("D")) {
                        ThongTinSoDoGhe.Ghe[3][Cot - 1] = true;
                    }

                }
            }
        };
        btnA1.setOnClickListener(onClickListener);
        btnA2.setOnClickListener(onClickListener);
        btnA3.setOnClickListener(onClickListener);
        btnA4.setOnClickListener(onClickListener);
        btnA5.setOnClickListener(onClickListener);
        btnB1.setOnClickListener(onClickListener);
        btnB2.setOnClickListener(onClickListener);
        btnB3.setOnClickListener(onClickListener);
        btnB4.setOnClickListener(onClickListener);
        btnB5.setOnClickListener(onClickListener);
        btnC1.setOnClickListener(onClickListener);
        btnC2.setOnClickListener(onClickListener);
        btnC3.setOnClickListener(onClickListener);
        btnC4.setOnClickListener(onClickListener);
        btnC5.setOnClickListener(onClickListener);
        btnD1.setOnClickListener(onClickListener);
        btnD2.setOnClickListener(onClickListener);
        btnD3.setOnClickListener(onClickListener);
        btnD4.setOnClickListener(onClickListener);
        btnD5.setOnClickListener(onClickListener);
    }



        }
