package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

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
import java.util.ArrayList;
import java.util.List;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;

    private ImageButton imageButtonDoiAnh;
    private EditText editTextHoVaTen;
    private EditText editTextSDT;
    private EditText editTextNgaySinh;
    private Button btnThoat;
    private Button btnLuu;


    private String UpdateTT;
    private String URLUpdateTTTK = "";
    private String LayTT;
    private String URLLayTTTK = "http://0306181355.pixelcent.com/Cinema/ThongTinKhachHang.php?ID=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
        spnTinhTp = findViewById(R.id.spinnerThanhPho);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyen);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuong);
        imageButtonDoiAnh = findViewById(R.id.imageButtonDoiAnh);
        editTextHoVaTen = findViewById(R.id.editTextHVT);
        editTextSDT = findViewById(R.id.editTextSDTTT);
        editTextNgaySinh = findViewById(R.id.editTextDateTT);
        btnThoat = findViewById(R.id.btnThoatTD);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chonTP();
        chonQuan();
        chonPhuong();
        HienThiThongTin hienThiThongTin = new HienThiThongTin();
        hienThiThongTin.execute();
       //HienThiThongTinUser();
    }

    /*private void HienThiThongTinUser() {
        try {
            ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(this, IDUser.idUser);
            txtHoVaTen.setText(thongTinUser.getHoVaTen());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/

    public void chonTP() {
        List<String> list = new ArrayList<>();
        list.add("");
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
        list.add("");
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
        list.add("");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnXaPhuong.setAdapter(adapter);
    }

    private class HienThiThongTin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                LayTT = URLLayTTTK + IDUser.idUser;
                try {
                    url = new URL(LayTT);
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

                for(int i = 0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String Hinh = jsonObject1.getString("Hinh");
                    String HoTen = jsonObject1.getString("HoTen");
                    String NgaySinh = jsonObject1.getString("NgaySinh");
                    String SDT = jsonObject1.getString("SDT");

                    int resID =getResources().getIdentifier(Hinh,"drawable",getPackageName());
                    imageButtonDoiAnh.setImageResource(resID);
                    editTextHoVaTen.setText(HoTen);
                    editTextSDT.setText(SDT);
                    editTextNgaySinh.setText(NgaySinh);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}