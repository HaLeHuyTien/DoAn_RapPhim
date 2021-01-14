package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class ThayDoiMatKhau extends AppCompatActivity {
    //URL API
    private String jsonUpdateMKURL;
    private final String updateMKURL = "http://0306181355.pixelcent.com/Cinema/ThayDoiMatKhau.php?MatKhau=";
    private String jsonMKURL;
    private final String matkhauURL = "http://0306181355.pixelcent.com/Cinema/ThongTinKhachHang.php?ID=";

    private EditText editTextMKC;
    private EditText editTextMKM;
    private EditText editTextNLMK;
    private String matKhau;
    private ImageButton imgHideMKC;
    private ImageButton imgHideMKM;

    //Bien hien an password
    private Integer x = 0;
    private Integer y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau);
        editTextMKC = findViewById(R.id.editTextMKCuTT);
        editTextMKM = findViewById(R.id.editTextMKMoiTT);
        editTextNLMK = findViewById(R.id.editTextNLMKTT);
        imgHideMKC = findViewById(R.id.imgHideMKC);
        imgHideMKM = findViewById(R.id.imgHideMKM);
        GetMatKhau getMatKhau = new GetMatKhau();
        getMatKhau.execute();
    }

    public void LuuThayDoi(View view) {
        if (editTextMKC.getText().toString().equals("") || editTextMKM.getText().toString().equals("") || editTextNLMK.getText().toString().equals("")) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin thay đổi !", Toast.LENGTH_SHORT).show();
        } else if (editTextMKM.getText().length() < 6) {
            Toast.makeText(this, "Mật khẩu mới phải nhiều hơn 6 ký tự !", Toast.LENGTH_SHORT).show();
        } else if (editTextMKC.getText().toString().equals(editTextMKM.getText().toString())) {
            Toast.makeText(this, "Mật khẩu mới đã trùng với mật khẩu cũ !", Toast.LENGTH_SHORT).show();
        } else if (editTextMKM.getText().toString().equals(editTextNLMK.getText().toString())) {
            if (editTextMKC.getText().toString().equals(matKhau)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thông Báo");

                // Ask the final question
                builder.setMessage("Đồng ý lưu thay đổi mật khẩu ?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        getThayDoiMatKhau getThayDoiMatKhau = new getThayDoiMatKhau();
                        getThayDoiMatKhau.execute();
                        finish();
                        Toast.makeText(ThayDoiMatKhau.this, "Thay đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            } else {
                Toast.makeText(this, "Mật khẩu cũ không chính xác !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Nhập lại mật khẩu không chính xác, vui lòng nhập lại !", Toast.LENGTH_SHORT).show();
        }
    }


    public void Thoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");

        // Ask the final question
        builder.setMessage("Đồng ý thoát ?");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                finish();
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    public void HideAndShowMKC(View view) {
        if (x == 0) {
            editTextMKC.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            x = 1;
        } else {
            editTextMKC.setTransformationMethod(PasswordTransformationMethod.getInstance());

            x = 0;
        }
    }

    public void HideAndShowMKM(View view) {
        if (y == 0) {
            editTextMKM.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            y = 1;
        } else {
            editTextMKM.setTransformationMethod(PasswordTransformationMethod.getInstance());

            y = 0;
        }
    }


    private class getThayDoiMatKhau extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    jsonUpdateMKURL = updateMKURL + editTextMKM.getText().toString() + "&id=" + IDUser.idUser.toString();
                    url = new URL(jsonUpdateMKURL);

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
        }
    }

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