package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Context;

import com.example.doan_rapphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadThongTinUserJson {
    public static ThongTinUser readThongTinUserFile(Context context, Integer ID) throws IOException, JSONException {
        ThongTinUser thongTinUser = new ThongTinUser();

        String jsonText = readText(context, R.raw.thongtinusers);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        JSONObject jsonObject = jsonArray.getJSONObject(ID);

        Integer Id = jsonObject.getInt("ID");
        String HVT = jsonObject.getString("HoVaTen");
        String Email = jsonObject.getString("Email");
        String SDT = jsonObject.getString("SDT");
        String NgaySinh = jsonObject.getString("NgaySinh");
        String DiaChi = jsonObject.getString("DiaChi");
        String MatKhau = jsonObject.getString("MatKhau");
        String Anh = jsonObject.getString("Anh");
        Integer TrangThai = jsonObject.getInt("TrangThai");

        thongTinUser.setID(Id);
        thongTinUser.setHoVaTen(HVT);
        thongTinUser.setEmail(Email);
        thongTinUser.setSDT(SDT);
        thongTinUser.setNgaySinh(NgaySinh);
        thongTinUser.setDiaChi(DiaChi);
        thongTinUser.setMatKhau(MatKhau);
        thongTinUser.setAnh(Anh);
        thongTinUser.setTrangThai(TrangThai);
        return thongTinUser;
    }
    public static Integer SoLuongTaiKhoan(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.thongtinusers);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("DanhSach");
        return jsonArray.length();
    }
    private static String readText(Context context, int resId) throws IOException{
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
