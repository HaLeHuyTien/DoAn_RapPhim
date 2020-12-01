package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;

    private ImageView txtanhDaiDien;
    private EditText txtHoVaTen;
    private EditText txtSDT;
    private EditText txtNgaySinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
        spnTinhTp = findViewById(R.id.spinnerThanhPho);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyen);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuong);
        txtanhDaiDien = findViewById(R.id.imgHinhDaiDienThayDoi);
        txtHoVaTen = findViewById(R.id.editTextHVT);
        txtSDT = findViewById(R.id.editTextSDTTT);
        txtNgaySinh = findViewById(R.id.editTextDateTT);
        chonTP();
        chonQuan();
        chonPhuong();
        HienThiThongTinUser();
    }

    private void HienThiThongTinUser() {
        try {
            ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(this, IDUser.idUser);
            txtHoVaTen.setText(thongTinUser.getHoVaTen());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void chonTP() {
        List<String> list = new ArrayList<>();
        list.add("Cả nước");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTinhTp.setAdapter(adapter);
    }
    public void chonQuan() {
        List<String> list = new ArrayList<>();
        list.add("Quận 1");
        list.add("Quận 2");
        list.add("Quận 3");
        list.add("Quận 4");
        list.add("Quận 5");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnHuyenQuan.setAdapter(adapter);
    }
    public void chonPhuong() {
        List<String> list = new ArrayList<>();
        list.add("Cả nước");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnXaPhuong.setAdapter(adapter);
    }
}