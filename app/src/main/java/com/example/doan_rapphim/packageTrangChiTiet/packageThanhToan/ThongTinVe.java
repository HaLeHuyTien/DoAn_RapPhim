package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doan_rapphim.R;

public class ThongTinVe extends AppCompatActivity {
private Button btnThayDoi;
private TextView txtTTGD;
private  TextView txtSL;
private String GheDaChon = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ve);
        btnThayDoi= findViewById(R.id.btnTroVe);
        txtSL = findViewById(R.id.txtTTGDSoLuong);
        txtSL.setText(ThongTinSoDoGhe.sl.toString());
        txtTTGD = findViewById(R.id.txtTTGDGhe);
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(ThongTinVe.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.SoDoGhe.class);
                startActivity(intent);
            }
        });
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
                    GheDaChon = GheDaChon + Hang + Cot.toString() + ", ";

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
        txtTTGD.setText("");
        txtTTGD.setText(GheDaChon);

    }
}