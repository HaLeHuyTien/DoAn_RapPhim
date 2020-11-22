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

public class ReadBinhLuanJson {

    public static BinhLuan_Json readBinhLuanJsonFile(Context context, int ViTriBinhLuan) throws IOException,JSONException{

        BinhLuan_Json binhLuan_json = new BinhLuan_Json();
        String jsonText = readText(context,R.raw.tct_binhluan);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");

        JSONObject jsonObject = jsonArray.getJSONObject(ViTriBinhLuan);
        Integer IDPhim = jsonObject.getInt("IDPhim");
        String tennguoibl = jsonObject.getString("TenNguoiBinhLuan");
        String noidungbl = jsonObject.getString("NoiDungBinhLuan");
        String anhnguoibl = jsonObject.getString("AnhNguoiBinhLuan");
        String ngaybl =  jsonObject.getString("NgayBinhLuan");

        binhLuan_json.setIDPhim(IDPhim);
        binhLuan_json.setTenNguoiBinhLuan(tennguoibl);
        binhLuan_json.setNoiDungBinhLuan(noidungbl);
        binhLuan_json.setAnhNguoiBinhLuan(anhnguoibl);
        binhLuan_json.setNgayBinhLuan(ngaybl);

        return binhLuan_json;
    }

    public static Integer SoLuongBinhLuan(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.tct_binhluan);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        return jsonArray.length();
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
