package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_rapphim.R;

public class ThanhToan extends AppCompatActivity {

    private Button btnThanhToan;
    private ImageView imgTenHinh;
    private TextView txtTT_TenPhim;
    private TextView txtTT_LuuY;
    private TextView txtTT_Ngay;
    private TextView txtTT_KhungGio;
    private TextView txtTT_Rap;
    private TextView txtTT_Ghe;
    private TextView txtTT_TTien;
    private TextView txtTT_Sl;
    private String GheDaChon = "";
    private Integer donGia = 85000;

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
        txtTT_TTien = findViewById(R.id.txtTien2);
        txtTT_Sl = findViewById(R.id.txtSoluong);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        imgTenHinh = findViewById(R.id.imgHinh);
        int ResiD = this.getResources().getIdentifier(ThongTinSoDoGhe.tenHinh,"drawable",this.getPackageName());
        imgTenHinh.setImageResource(ResiD);
        ThongTinSoDoGhe.tongTien = donGia * ThongTinSoDoGhe.sl;
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToan.this, ThongTinGiaoDich.class);
                startActivity(intent);
            }
        });
        HiennThiDanhSach();
        for(int i = 0; i < 4; i++){
            for(Integer j = 0; j < 5 ; j++){
                if(ThongTinSoDoGhe.Ghe[i][j] == true){
                    String Hang = "";
                    if(i == 0)
                        Hang = "A";
                    if(i == 1)
                        Hang = "B";
                    if(i == 2)
                        Hang = "C";
                    if(i == 3)
                        Hang = "D";
                    Integer Cot = j + 1;
                    GheDaChon = GheDaChon + Hang + Cot.toString() + ",";

                }
            }
        }
        String[] separated = GheDaChon.split(",");
        GheDaChon = "";
        for(int i = 0; i < separated.length;i++)
        {
            GheDaChon = GheDaChon + separated[i];
            if(i == separated.length-1)
            {
                GheDaChon = GheDaChon + ".";
            }
            else {
                GheDaChon = GheDaChon + ", ";
            }
        }
        txtTT_Ghe.setText("");
        txtTT_Ghe.setText(GheDaChon);

    }

    public void HiennThiDanhSach() {
        try {
            ThanhToanJson thanhToanJson = ReadThanhToanJson.readThanhToanJsonFile(this);
            txtTT_TenPhim.setText(ThongTinSoDoGhe.tenPhim);
            txtTT_LuuY.setText(thanhToanJson.getLuuY());
            txtTT_Ngay.setText(ThongTinSoDoGhe.NgayChieu);
            txtTT_KhungGio.setText(thanhToanJson.getKhungGio());
            txtTT_Rap.setText(thanhToanJson.getRap());
            txtTT_TTien.setText(ThongTinSoDoGhe.tongTien.toString());
            txtTT_Sl.setText(ThongTinSoDoGhe.sl.toString());

        } catch (Exception e) {
            txtTT_TenPhim.setText("Loi !!");
        }


    }
}