package com.example.doan_rapphim;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class readJsonListPhim {
    public static Phim readTuDienJson(Context context, int i) throws IOException, JSONException {
        String jsonText=readText(context,R.raw.dsphim);

        JSONObject jsonRoot=new JSONObject(jsonText);

        JSONArray jsonArray=jsonRoot.getJSONArray("DanhSach");
        JSONObject jsonObject2=jsonArray.getJSONObject(i);
        String mtenPhim=jsonObject2.getString("TenPhim");
        String mtheLoai=jsonObject2.getString("TheLoai");
        String mdoTuoi=jsonObject2.getString("DoTuoi");
        String msoDiem=jsonObject2.getString("SoDiem");
        String mhinhAnh=jsonObject2.getString("HinhAnh");
        String mtrangThai=jsonObject2.getString("TrangThai");
        Phim phim=new Phim();
        phim.setTenPhim(mtenPhim);
        phim.setTheLoai(mtheLoai);
        phim.setDoTuoi(mdoTuoi);
        phim.setHinhAnh(mhinhAnh);
        phim.setSoDiem(msoDiem);
        phim.setTrangThai(mtrangThai);

        return phim;
    }
    public static Integer SoLuongPhim(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.dsphim);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        return jsonArray.length();
    }
    private static String readText(Context context,int resId)throws IOException{
        InputStream is=context.getResources().openRawResource(resId);
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String s=null;
        while ((s=br.readLine())!=null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
