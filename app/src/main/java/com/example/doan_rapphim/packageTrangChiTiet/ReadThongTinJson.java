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

public class ReadThongTinJson {

    public static ThongTinJson readThongTinJsonFile(Context context, int IDPhim) throws IOException, JSONException {

        ThongTinJson thongTinJson = new ThongTinJson();
        String jsonText = readText(context, R.raw.tct_thongtin);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");

        JSONObject jsonObject = jsonArray.getJSONObject(IDPhim);
        Integer idPhim = jsonObject.getInt("IDPhim");
        String HinhPhim = jsonObject.getString("HinhPhim");
        String TenPhim = jsonObject.getString("TenPhim");
        String TomTat = jsonObject.getString("TomTat");
        String NgayKhoiChieu = jsonObject.getString("NgayKhoiChieu");
        String ThoiLuong = jsonObject.getString("ThoiLuong");
        String TheLoai = jsonObject.getString("TheLoai");
        String NgonNgu = jsonObject.getString("NgonNgu");
        String DoTuoi = jsonObject.getString("DoTuoi");
        Double Diem = jsonObject.getDouble("Diem");
        String Trailer = jsonObject.getString("Trailer");
        String DaoDien = jsonObject.getString("DaoDien");
        String HinhDD = jsonObject.getString("HinhDaoDien");

        thongTinJson.setIDPhim(idPhim);
        thongTinJson.setHinhPhim(HinhPhim);
        thongTinJson.setTenPhim(TenPhim);
        thongTinJson.setTomTat(TomTat);
        thongTinJson.setNgayKhoiChieu(NgayKhoiChieu);
        thongTinJson.setThoiLuong(ThoiLuong);
        thongTinJson.setTheLoai(TheLoai);
        thongTinJson.setNgonNgu(NgonNgu);
        thongTinJson.setDoTuoi(DoTuoi);
        thongTinJson.setDiem(Diem);
        thongTinJson.setTrailer(Trailer);
        thongTinJson.setDaoDien(DaoDien);
        thongTinJson.setHinhDaoDien(HinhDD);


        return thongTinJson;
    }

    public static Integer SoLuongPhim(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.tct_thongtin);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        return jsonArray.length();
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
