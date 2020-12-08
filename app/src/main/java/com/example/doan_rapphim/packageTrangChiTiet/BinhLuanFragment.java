package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

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
import java.util.PrimitiveIterator;

public class BinhLuanFragment extends Fragment {


    private  String jsonURLBL;
    private  String value= "http://0306181355.pixelcent.com/Cinema/BinhLuanTheoPhim.php?IDPhim=";

    private Button btn;
    //RecyclerView
    private final LinkedList<BinhLuan_Json> mWordList = new LinkedList<>();
    private RecyclerView recyclerView;
    private BinhLuanListAdapter adapter;
    private TrangChiTiet mtrangChiTiet;

    public static BinhLuanFragment getInstance(){
        BinhLuanFragment binhLuanFragment = new BinhLuanFragment();
        return  binhLuanFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.binhluan, container,false);
        recyclerView = view.findViewById(R.id.recycler_view_binh_luan);
        btn = view.findViewById(R.id.btnDangBinhLuan);
        mtrangChiTiet = (TrangChiTiet) getActivity();
        jsonURLBL = value + mtrangChiTiet.getIdPhim().toString();

        GetBinhLuan getBinhLuan = new GetBinhLuan();
        getBinhLuan.execute();

        return view;

    }

    public void HiennThiDanhSach(){
        try {
            Integer soluongBinhLuan =ReadBinhLuanJson.SoLuongBinhLuan(getActivity());
            mWordList.clear();
            for(Integer i = 0; i < soluongBinhLuan; i++){
                BinhLuan_Json binhLuan_json = ReadBinhLuanJson.readBinhLuanJsonFile(getActivity(),i);
                if(binhLuan_json.getIDPhim() == mtrangChiTiet.getIdPhim())
                mWordList.addLast(binhLuan_json);
            }
            adapter = new BinhLuanListAdapter(getActivity(),mWordList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }catch (Exception e){
            btn.setText("Lỗi");
        }

    }

    private class GetBinhLuan extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURLBL);
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

                mWordList.clear();

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String HoTen = jsonObject1.getString("HoTen");
                    String NoiDung = jsonObject1.getString("NoiDung");
                    String Ngay = jsonObject1.getString("Ngay");
                    String Gio = jsonObject1.getString("Gio");

                    BinhLuan_Json binhLuan_json = new BinhLuan_Json();
                    binhLuan_json.setNgayBinhLuan(Ngay);
                    binhLuan_json.setAnhNguoiBinhLuan("dienvien");
                    binhLuan_json.setTenNguoiBinhLuan(HoTen);
                    binhLuan_json.setNoiDungBinhLuan(NoiDung);
                    binhLuan_json.setGioBinhLuan(Gio);

                    mWordList.addLast(binhLuan_json);
                }
                adapter = new BinhLuanListAdapter(getActivity(),mWordList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
