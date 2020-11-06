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

    public static LichChieu_Json[] readLichChieuJsonFile(Context context) throws IOException, JSONException{


        LichChieu_Json[] lichChieu_json = new LichChieu_Json[2];
        for(int j = 0; j < 2 ; j++) {
            String jsonText = readText(context,R.raw.lichchieu);
            JSONObject jsonRoot = new JSONObject(jsonText).getJSONObject("Galaxy"+(j+1));

            String TenRap = jsonRoot.getString("TenRap");
            String TenTinh = jsonRoot.getString("TenTinh");

            JSONArray jsonArray = jsonRoot.getJSONArray("XuatChieu");
            String[] XuatChieu = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                XuatChieu[i] = jsonArray.getString(i);
            }


            lichChieu_json[j].setTenRap(TenRap);
            lichChieu_json[j].setTenTinh(TenTinh);
            lichChieu_json[j].setXuatChieu(XuatChieu);
        }


        return lichChieu_json;
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
