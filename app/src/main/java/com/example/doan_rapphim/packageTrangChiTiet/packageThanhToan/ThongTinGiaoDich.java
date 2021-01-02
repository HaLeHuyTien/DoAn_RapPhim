package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinFragment;

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

public class ThongTinGiaoDich extends AppCompatActivity {

    private Button btnThayDoi;
    private TextView txtTTGD;
    private  TextView txtSL;
    private TextView txtTTGDtong;
    private String GheDaChon = "";
    private  String InsertVe = "http://0306181355.pixelcent.com/Cinema/VePhim.php?IDKhachHang=";
    private String DataGhe = "http://0306181355.pixelcent.com/Cinema/Ghe.php";
    private String jsonURL;
    private final LinkedList<Ghe> mWordList = new LinkedList<>();
    String chuoi = "Ds: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_giao_dich);

        btnThayDoi= findViewById(R.id.btnTroVe);
        txtSL = findViewById(R.id.txtTTGDSoLuong);
        txtTTGDtong = findViewById((R.id.txtTTGDTongTien));
        txtTTGD = findViewById(R.id.txtTTGDGhe);
        Hienthids();
        GetGhe getGhe = new GetGhe();
        getGhe.execute();
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(ThongTinGiaoDich.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.SoDoGhe.class);
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
    private void Hienthids(){
        txtSL.setText(ThongTinSoDoGhe.sl.toString());
        txtTTGDtong.setText(ThongTinSoDoGhe.tongTien.toString());
    }
    private class GetGhe extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(DataGhe);
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
                mWordList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String Cot = jsonObject1.getString("SoCot");
                        String Hang = jsonObject1.getString("SoHang");
                        Ghe ghe = new Ghe();
                        ghe.setCot(Cot);
                        ghe.setHang(Hang);
                        chuoi +=  ghe.getCot().toString() + ghe.getHang().toString() + "; ";
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }
}