package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;

import com.example.doan_rapphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadDienVienJson {

    public static DienVienJson readDienVienJsonFile(Context context, int IDPhim, int vitri) throws IOException, JSONException {

        DienVienJson dienVienJson = new DienVienJson();
        String jsonText = readText(context, R.raw.tct_thongtin);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");

        JSONObject jsonObject = jsonArray.getJSONObject(IDPhim);
        JSONArray jsonArray2 = jsonObject.getJSONArray("DienVien");
        JSONObject jsonObject2 = jsonArray2.getJSONObject(vitri);
        String HinhDV = jsonObject2.getString("HinhDV");
        String TenDV = jsonObject2.getString("TenDV");


       dienVienJson.setHinhDV(HinhDV);
       dienVienJson.setTenDV(TenDV);

        return dienVienJson;
    }

    public static Integer SoLuongDienVien(Context context, int IDPhim) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.tct_thongtin);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");

        JSONObject jsonObject = jsonArray.getJSONObject(IDPhim);
        JSONArray jsonArray2 = jsonObject.getJSONArray("DienVien");
        return jsonArray2.length();
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
