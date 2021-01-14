package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class XacThucMatKhauActivity extends AppCompatActivity {

    private String jsonMKURL;
    private final String matkhauURL = "http://0306181355.pixelcent.com/Cinema/ThongTinKhachHang.php?ID=";

    private EditText editTextNhapMK;
    private Button btnTiepTheo;
    private Button btnThoat;

    private Integer x = 0;

    private String matKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_thuc_mat_khau);

        editTextNhapMK = findViewById(R.id.editTextNhapXacThuc);
        btnTiepTheo = findViewById(R.id.btnTiepTheoXT);
        btnThoat = findViewById(R.id.btnThoatXT);

        //Nút thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TiepTheo();
            }


        });

        GetMatKhau getMatKhau = new GetMatKhau();
        getMatKhau.execute();
    }

    private void TiepTheo() {
        if (editTextNhapMK.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu !", Toast.LENGTH_SHORT).show();
        } else if (editTextNhapMK.getText().toString().equals(matKhau)) {
            Intent intent = new Intent(XacThucMatKhauActivity.this, ThayDoiThongTin.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
        }
    }

    //Nút hiện mật khẩu
    public void HideAndShowNhapMK(View view) {
        if (x == 0) {
            editTextNhapMK.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            x = 1;
        } else {
            editTextNhapMK.setTransformationMethod(PasswordTransformationMethod.getInstance());

            x = 0;
        }
    }

    //API lấy mật khẩu về để so sánh
    private class GetMatKhau extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    jsonMKURL = matkhauURL + IDUser.idUser;
                    url = new URL(jsonMKURL);
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
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String MatKhau = jsonObject1.getString("MatKhau");

                    matKhau = MatKhau;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}