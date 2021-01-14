package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.content.Context;

import com.example.doan_rapphim.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadThanhToanJson {
    public static ThanhToanJson readThanhToanJsonFile(Context context) throws IOException, JSONException {
        ThanhToanJson thanhToanJson = new ThanhToanJson();
        String jsonText = readText(context, R.raw.thanhtoan);
        JSONObject jsonRoot = new JSONObject(jsonText);
        Integer idPhim = jsonRoot.getInt("ID");
        String tenPhim = jsonRoot.getString("TenPhim");
        String luuY = jsonRoot.getString("LuuY");
        String ngay = jsonRoot.getString("Ngay");
        String khungGio = jsonRoot.getString("KhungGio");
        String rap = jsonRoot.getString("Rap");
        String ghe = jsonRoot.getString("Ghe");
        Integer tongTien = jsonRoot.getInt("TongTien");
        Integer soLuong = jsonRoot.getInt("SoLuong");

        thanhToanJson.setID(idPhim);
        thanhToanJson.setTenPhim(tenPhim);
        thanhToanJson.setLuuY(luuY);
        thanhToanJson.setNgay(ngay);
        thanhToanJson.setKhungGio(khungGio);
        thanhToanJson.setRap(rap);
        thanhToanJson.setGhe(ghe);
        thanhToanJson.setTong(tongTien);
        thanhToanJson.setSl(soLuong);

        return thanhToanJson;
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
