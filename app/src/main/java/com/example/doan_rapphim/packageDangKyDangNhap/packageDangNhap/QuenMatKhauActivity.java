package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy.DangKyActivity;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TabTaiKhoan_Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QuenMatKhauActivity extends AppCompatActivity {

    private String URLQuenMK = "";
    private String value="http://0306181355.pixelcent.com/Cinema/KiemTraSDTQuenMatKhau.php?SDT=";
    //private  String value = "http://0306181355.pixelcent.com/Cinema/QuenMatKhau.php?Email=";
    private EditText edtQuenMK;
    private Button btnQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        edtQuenMK = findViewById(R.id.edtNhapEmailQuenMK);
        btnQuenMK = findViewById(R.id.btnTimTKQuenMK);
        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuenMK quenMK = new QuenMK();
                quenMK.execute();
            }
        });
    }

    private class QuenMK extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    URLQuenMK = value + edtQuenMK.getText().toString();
                    url = new URL(URLQuenMK);
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

            }catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");


                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                String MatKhau = jsonObject1.getString("MatKhau");
                if(MatKhau == edtQuenMK.getText().toString())
                {
                    Toast.makeText(QuenMatKhauActivity.this,"Số điện thoại tồn tại và Mật khẩu của bạn: "+MatKhau,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(QuenMatKhauActivity.this,"Số điện thoại bạn không tồn tại.",Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}