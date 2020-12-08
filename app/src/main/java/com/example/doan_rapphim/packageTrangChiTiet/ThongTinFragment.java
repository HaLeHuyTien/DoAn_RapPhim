package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.doan_rapphim.AdapterListPhimItem;
import com.example.doan_rapphim.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

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

public class ThongTinFragment extends Fragment {

    private TrangChiTiet trangChiTiet;

    private  String jsonURL = "http://0306181355.pixelcent.com/Cinema/Phim.php";
    private  String jsonURLDV;
    private  String value= "http://0306181355.pixelcent.com/Cinema/DienVienTheoPhim.php?idphim=";



    private ImageView imgHinhPhim;
    private  TextView txtTenPhim;
    private ReadMoreTextView txtTomTat;
    private TextView txtNgayKhoiChieu;
    private TextView txtThoiLuong;
    private TextView txtTheLoai;
    private TextView txtNhaSanXuat;
    private ImageView imgXH1;
    private ImageView imgXH2;
    private ImageView imgXH3;
    private ImageView imgXH4;
    private ImageView imgXH5;
    private TextView txtDiem;
    private TextView txtDoTuoi;
    private YouTubePlayerView ytbTrailer;

    private final LinkedList<DienVienJson> mWordList = new LinkedList<>();
    private RecyclerView rvDienVien;
    private DienVienListAdapter adapter;


    private Button btnDatVe;
    public static ThongTinFragment getInstance(){
        ThongTinFragment thongTinFragment = new ThongTinFragment();
        return  thongTinFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin, container,false);

        trangChiTiet = (TrangChiTiet) getActivity();

        jsonURLDV = value + trangChiTiet.getIdPhim().toString();


        //TrangChiTiet
        imgHinhPhim = view.findViewById(R.id.imgHinhPhim);
        txtTenPhim = view.findViewById(R.id.txtTenPhim);
        txtTomTat = view.findViewById(R.id.txtTomTat);
        txtNgayKhoiChieu = view.findViewById(R.id.txtNgayKhoiChieu);
        txtThoiLuong = view.findViewById(R.id.txtThoiLuong);
        txtTheLoai = view.findViewById(R.id.txtTheLoai);
        txtNhaSanXuat = view.findViewById(R.id.txtNhaSanXuat);
        imgXH1 = view.findViewById(R.id.imgXH1);
        imgXH2 = view.findViewById(R.id.imgXH2);
        imgXH3 = view.findViewById(R.id.imgXH3);
        imgXH4 = view.findViewById(R.id.imgXH4);
        imgXH5 = view.findViewById(R.id.imgXH5);
        txtDiem = view.findViewById(R.id.txtDiem);
        txtDoTuoi = view.findViewById(R.id.txtDoTuoi);
        ytbTrailer = view.findViewById(R.id.youtube_player_view);

        rvDienVien = view.findViewById(R.id.rvDienVien);

        //Gan vao` Trang Chi Tiet
       /* try {
            ThongTinJson thongTinJson = ReadThongTinJson.readThongTinJsonFile(getActivity(),trangChiTiet.getIdPhim());
            int resId = this.getContext().getResources().getIdentifier(thongTinJson.getHinhPhim(),"drawable",getContext().getPackageName());
            imgHinhPhim.setImageResource(resId);
            txtTenPhim.setText(thongTinJson.getTenPhim());
            txtTomTat.setText(thongTinJson.getTomTat());
            txtNgayKhoiChieu.setText(thongTinJson.getNgayKhoiChieu());
            txtThoiLuong.setText(thongTinJson.getThoiLuong());
            txtTheLoai.setText(thongTinJson.getTheLoai());
            txtNhaSanXuat.setText(thongTinJson.getNhaSanXuat());
            txtDiem.setText(thongTinJson.getDiem().toString());
            txtDoTuoi.setText(thongTinJson.getDoTuoi());
            ytbTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(thongTinJson.getTrailer(),0);
                    youTubePlayer.pause();
                }


            });
            DienVienJson daodien = new DienVienJson();
            daodien.setTenDV(thongTinJson.getDaoDien());
            daodien.setHinhDV(thongTinJson.getHinhDaoDien());
            mWordList.addLast(daodien);

            int soluongDV = ReadDienVienJson.SoLuongDienVien(getActivity(),thongTinJson.getIDPhim());
            for (int i = 0; i< soluongDV ; i++) {
                DienVienJson dienVienJson = ReadDienVienJson.readDienVienJsonFile(getActivity(), thongTinJson.getIDPhim(),i);
                mWordList.addLast(dienVienJson);
            }
            adapter = new DienVienListAdapter(getActivity(),mWordList);
           rvDienVien.setAdapter(adapter);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
           rvDienVien.setLayoutManager(manager);
        }catch (Exception e)
        {
            txtTenPhim.setText("Lỗi");
        }*/

        GetTrangChiTet getTrangChiTet = new GetTrangChiTet();
        getTrangChiTet.execute();
        GetDienVien getDienVien = new GetDienVien();
        getDienVien.execute();


        btnDatVe = view.findViewById(R.id.btnDatVe);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThanhToan.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private class GetTrangChiTet extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURL);
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

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");


                    JSONObject jsonObject1 = jsonArray.getJSONObject(trangChiTiet.getIdPhim()-1);
                    String id = jsonObject1.getString("id");
                    String TenPhim = jsonObject1.getString("TenPhim");
                    String LoaiPhim = jsonObject1.getString("LoaiPhim");
                    String DaoDien = jsonObject1.getString("DaoDien");
                    String HinhDaoDien = jsonObject1.getString("HinhDaoDien");
                    String ThoiLuong = jsonObject1.getString("ThoiLuong");
                    String GioiHanTuoi = jsonObject1.getString("GioiHanTuoi");
                    String VideoTrailer = jsonObject1.getString("VideoTrailer");
                    String NoiDung = jsonObject1.getString("NoiDung");
                    String Hinh = jsonObject1.getString("Hinh");
                    String NhaSanXuat = jsonObject1.getString("NhaSanXuat");

                    int resId = getContext().getResources().getIdentifier(Hinh,"drawable",getContext().getPackageName());
                    imgHinhPhim.setImageResource(resId);
                    txtTenPhim.setText(TenPhim);
                    txtTomTat.setText(NoiDung);
                    txtNgayKhoiChieu.setText("30/11/2020");
                    txtThoiLuong.setText(ThoiLuong);
                    txtTheLoai.setText(LoaiPhim);
                    txtNhaSanXuat.setText(NhaSanXuat);
                    txtDiem.setText("9.0");
                    txtDoTuoi.setText(GioiHanTuoi);
                    ytbTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(YouTubePlayer youTubePlayer) {
                            youTubePlayer.loadVideo(VideoTrailer,0);
                            youTubePlayer.pause();
                        }


                    });

                DienVienJson daodien = new DienVienJson();
                daodien.setTenDV(DaoDien);
                daodien.setHinhDV(HinhDaoDien);
                mWordList.addLast(daodien);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetDienVien extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURLDV);
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

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String TenDienVien = jsonObject1.getString("TenDienVien");
                    String Hinh = jsonObject1.getString("Hinh");


                    DienVienJson daodien = new DienVienJson();
                    daodien.setTenDV(TenDienVien);
                    daodien.setHinhDV(Hinh);
                    mWordList.addLast(daodien);
                }
                adapter = new DienVienListAdapter(getActivity(),mWordList);
                rvDienVien.setAdapter(adapter);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
                rvDienVien.setLayoutManager(manager);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
