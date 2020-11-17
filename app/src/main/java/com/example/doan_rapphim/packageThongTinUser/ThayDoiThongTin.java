package com.example.doan_rapphim.packageThongTinUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.doan_rapphim.R;

import java.util.ArrayList;
import java.util.List;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
        spnTinhTp = findViewById(R.id.spinnerThanhPho);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyen);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuong);
        chonTP();
        chonQuan();
        chonPhuong();
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