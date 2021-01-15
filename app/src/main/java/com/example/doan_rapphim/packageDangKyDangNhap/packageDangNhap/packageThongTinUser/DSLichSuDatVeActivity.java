package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class DSLichSuDatVeActivity extends AppCompatActivity {
    private String jsonURL;
    private final String URLDSLSDatVe = "http://0306181355.pixelcent.com/rapphim/public/api/DanhSachDonDatVe/";

    private final LinkedList<ThongTinGiaoDich_Json> mWordList = new LinkedList<>();
    private GiaoDichAdapter giaoDichAdapter;
    private TextView txtTenPhim;
    private TextView txtTenRap;
    private TextView txtGhe;
    private TextView txtNgayDatVe;
    private TextView txtTongTien;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_s_lich_su_dat_ve);
        txtTenRap = findViewById(R.id.txtTenRap);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtGhe = findViewById(R.id.txtGhe);
        txtNgayDatVe = findViewById(R.id.txtNgayDat);
        txtTongTien = findViewById(R.id.txtTongTien);

        recyclerView = findViewById(R.id.recyclerViewGD);

        GetDanhSachLichSuDatVe getDanhSachLichSuDatVe = new GetDanhSachLichSuDatVe(this);
        getDanhSachLichSuDatVe.execute();
    }


    private class GetDanhSachLichSuDatVe extends AsyncTask<String, String, String> {

        Context context;

        public GetDanhSachLichSuDatVe(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                jsonURL = URLDSLSDatVe + IDUser.idUser.toString();
                try {
                    url = new URL(jsonURL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                mWordList.clear();

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String TenPhim = jsonObject1.getString("TenPhim");
                    String TenRap = jsonObject1.getString("TenRap");
                    String Ghe = jsonObject1.getString("Ghe");
                    String tongtien = jsonObject1.getString("tongtien");
                    String NgayDatVe = jsonObject1.getString("NgayDatVe");

                    ThongTinGiaoDich_Json thongTinGiaoDich_json = new ThongTinGiaoDich_Json();
                    thongTinGiaoDich_json.setTenPhim(TenPhim);
                    thongTinGiaoDich_json.setTenRap(TenRap);
                    thongTinGiaoDich_json.setGhe(Ghe);
                    thongTinGiaoDich_json.setNgayDatVe(NgayDatVe);
                    thongTinGiaoDich_json.setTongTien(tongtien);

                    mWordList.addLast(thongTinGiaoDich_json);
                }
                giaoDichAdapter = new GiaoDichAdapter(context, mWordList);
                recyclerView.setAdapter(giaoDichAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}