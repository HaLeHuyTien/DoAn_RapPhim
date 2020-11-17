package com.example.doan_rapphim.packageThongTinUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.doan_rapphim.R;

import java.util.ArrayList;
import java.util.List;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
    }

    public void chonThang() {
        List<String> list = new ArrayList<>();
        for(int i = 1;i<13;i++) {
            list.add("T "+ i);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTp.setAdapter(adapter);
    }
}