package com.example.doan_rapphim;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadLichChieuJson {

    public static LichChieu_Json readLichChieuJsonFile(Context context, Integer ViTriXuatChieu) throws IOException, JSONException {


        LichChieu_Json lichChieu_json = new LichChieu_Json();

        String jsonText = readText(context, R.raw.lichchieu);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray2 = jsonRoot.getJSONArray("DanhSach");

            JSONObject jsonObject = jsonArray2.getJSONObject(ViTriXuatChieu);
            String TenRap = jsonObject.getString("TenRap");
            String TenTinh = jsonObject.getString("TenTinh");
            String NgayChieu = jsonObject.getString("NgayChieu");
            JSONArray jsonArray = jsonObject.getJSONArray("XuatChieu");
            String[] XuatChieu = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                XuatChieu[i] = jsonArray.getString(i);
            }
            lichChieu_json.setTenRap(TenRap);
            lichChieu_json.setTenTinh(TenTinh);
            lichChieu_json.setNgayChieu(NgayChieu);
            lichChieu_json.setXuatChieu(XuatChieu);


        return lichChieu_json;
    }

     public static Integer SoLuongXuatChieu(Context context) throws IOException, JSONException{
        String jsonText = readText(context, R.raw.lichchieu);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        return jsonArray.length();
    }

    private static String readText(Context context,int resId) throws IOException{
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
