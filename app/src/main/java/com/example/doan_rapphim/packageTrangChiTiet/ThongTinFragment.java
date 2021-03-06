package com.example.doan_rapphim.packageTrangChiTiet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class ThongTinFragment extends Fragment {

    private TrangChiTiet trangChiTiet;


    private String jsonURL;
    private final String ChiTietPhim = "http://0306181355.pixelcent.com/rapphim/public/api/ChiTietPhim/";
    private String jsonURLDV;
    private final String value = "http://0306181355.pixelcent.com/rapphim/public/api/DienVienTheoPhim/";
    private String jsonURLDanhGia;
    private final String DanhGia = "http://0306181355.pixelcent.com/rapphim/public/api/DanhGiaTheoUser/";
    private String jsonURLDangDanhGia;

    private final String InsertDanhGia = "http://0306181355.pixelcent.com/rapphim/public/api/DangDanhGia/";
    private final String UpdateDanhGia = "http://0306181355.pixelcent.com/rapphim/public/api/CapNhatDanhGia/";
    private String DiemTheoPhim;
    private String URLDiem = "http://0306181355.pixelcent.com/rapphim/public/api/DiemTheoPhim/";

    private String XoaDanhGia = "http://0306181355.pixelcent.com/rapphim/public/api/XoaDanhGia/";

    private TextView txtDanhGiaNguoiDung;
    private TextView txtXoaDanhGia;


    private ImageView imgHinhPhim;
    private TextView txtTenPhim;
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
    private ImageView imgXH6;
    private ImageView imgXH7;
    private ImageView imgXH8;
    private ImageView imgXH9;
    private ImageView imgXH10;
    private TextView txtDiem;
    private TextView txtDoTuoi;
    private YouTubePlayerView ytbTrailer;

    private final LinkedList<DienVienJson> mWordList = new LinkedList<>();
    private RecyclerView rvDienVien;
    private DienVienListAdapter adapter;

    private String date;
    private String time;
    private String Diem;



    public static ThongTinFragment getInstance() {
        ThongTinFragment thongTinFragment = new ThongTinFragment();
        return thongTinFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin, container, false);

        trangChiTiet = (TrangChiTiet) getActivity();

        jsonURLDV = value + trangChiTiet.getIdPhim().toString();

        jsonURL = ChiTietPhim + trangChiTiet.getIdPhim().toString();

        jsonURLDanhGia = DanhGia + trangChiTiet.getIdPhim().toString() + "/" + IDUser.idUser;

        txtDanhGiaNguoiDung = view.findViewById(R.id.txtDanhGiaNguoiDung);
        txtXoaDanhGia = view.findViewById(R.id.txtXoaDanhGia);

        txtXoaDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = getResources().getResourceEntryName(v.getId()).substring(5);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Set a title for alert dialog
                builder.setTitle("Thông báo");

                // Ask the final question
                builder.setMessage("Bạn muốn xóa đánh giá phim này ?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        XoaDanhGia xoaDanhGia = new XoaDanhGia();
                        xoaDanhGia.execute();
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();

            }
        });


        DateFormat df = new SimpleDateFormat("yyyy-MM-d");
        date = df.format(Calendar.getInstance().getTime());
        DateFormat df2 = new SimpleDateFormat("HH:mm");
        time = df2.format(Calendar.getInstance().getTime());


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
        imgXH6 = view.findViewById(R.id.imgXH6);
        imgXH7 = view.findViewById(R.id.imgXH7);
        imgXH8 = view.findViewById(R.id.imgXH8);
        imgXH9 = view.findViewById(R.id.imgXH9);
        imgXH10 = view.findViewById(R.id.imgXH10);
        txtDiem = view.findViewById(R.id.txtDiem);
        txtDoTuoi = view.findViewById(R.id.txtDoTuoi);
        ytbTrailer = view.findViewById(R.id.youtube_player_view);
        rvDienVien = view.findViewById(R.id.rvDienVien);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (IDUser.idUser < 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    // Set a title for alert dialog
                    builder.setTitle("Thông Báo");

                    // Ask the final question
                    builder.setMessage("Vui lòng đăng nhập để đánh giá phim");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            // Set the TextView visibility GONE
                        }
                    });

                    // Set the alert dialog no button click listener


                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                } else {

                        String a = getResources().getResourceEntryName(v.getId()).substring(5);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        // Set a title for alert dialog
                        builder.setTitle("Đánh Giá");

                        // Ask the final question
                        builder.setMessage("Bạn muốn đánh giá phim này " + a + " điểm");

                        // Set the alert dialog yes button click listener
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do something when user clicked the Yes button
                                // Set the TextView visibility GONE
                                Diem = a;
                                DangDanhGia dangDanhGia = new DangDanhGia();
                                dangDanhGia.execute();

                                GetDanhGia getDanhGia = new GetDanhGia();
                                getDanhGia.execute();



                                txtXoaDanhGia.setVisibility(View.VISIBLE);
                            }
                        });

                        // Set the alert dialog no button click listener
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do something when No button clicked

                            }
                        });

                        AlertDialog dialog = builder.create();
                        // Display the alert dialog on interface
                        dialog.show();

                }
            }
        };

        imgXH1.setOnClickListener(onClickListener);
        imgXH2.setOnClickListener(onClickListener);
        imgXH3.setOnClickListener(onClickListener);
        imgXH4.setOnClickListener(onClickListener);
        imgXH5.setOnClickListener(onClickListener);
        imgXH6.setOnClickListener(onClickListener);
        imgXH7.setOnClickListener(onClickListener);
        imgXH8.setOnClickListener(onClickListener);
        imgXH9.setOnClickListener(onClickListener);
        imgXH10.setOnClickListener(onClickListener);




        GetTrangChiTet getTrangChiTet = new GetTrangChiTet();
        getTrangChiTet.execute();
        GetDienVien getDienVien = new GetDienVien();
        getDienVien.execute();
        GetDanhGia getDanhGia = new GetDanhGia();
        getDanhGia.execute();

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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");


                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                String id = jsonObject1.getString("id");
                String TenPhim = jsonObject1.getString("TenPhim");
                String LoaiPhim = jsonObject1.getString("TenLoai");
                String DaoDien = jsonObject1.getString("TenDaoDien");
                String HinhDaoDien = jsonObject1.getString("HinhDaoDien");
                String ThoiLuong = jsonObject1.getString("ThoiLuong");
                String GioiHanTuoi = jsonObject1.getString("GioiHanTuoi");
                String VideoTrailer = jsonObject1.getString("VideoTrailer");
                String NoiDung = jsonObject1.getString("NoiDung");
                String Hinh = jsonObject1.getString("Hinh");
                String NhaSanXuat = jsonObject1.getString("TenNhaSanXuat");
                Double Diem = jsonObject1.getDouble("Diem");
                String NgayKhoiChieu = jsonObject1.getString("NgayKhoiChieu");


                Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + Hinh).into(imgHinhPhim);
                txtTenPhim.setText(TenPhim);
                txtTomTat.setText(NoiDung);
                txtNgayKhoiChieu.setText(NgayKhoiChieu);
                txtThoiLuong.setText(ThoiLuong);
                txtTheLoai.setText(LoaiPhim);
                txtNhaSanXuat.setText(NhaSanXuat);
                txtDiem.setText(Diem.toString());
                txtDoTuoi.setText(GioiHanTuoi + "+");
                ytbTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(VideoTrailer, 0);
                        youTubePlayer.pause();
                    }


                });
                int x = 0;
                if (Double.parseDouble(txtDiem.getText().toString()) < 10) {
                    x = Integer.parseInt(txtDiem.getText().toString().substring(0, 1));
                } else {
                    x = Integer.parseInt(txtDiem.getText().toString().substring(0, 2));
                }

                DanhGiaDiem(x);
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

            } catch (Exception e) {
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
                adapter = new DienVienListAdapter(getActivity(), mWordList);
                rvDienVien.setAdapter(adapter);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                rvDienVien.setLayoutManager(manager);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void DanhGiaDiem(int a) {
        switch (a) {
            case 10:
                imgXH10.setImageResource(R.drawable.yellowstar);
            case 9:
                imgXH9.setImageResource(R.drawable.yellowstar);
            case 8:
                imgXH8.setImageResource(R.drawable.yellowstar);
            case 7:
                imgXH7.setImageResource(R.drawable.yellowstar);
            case 6:
                imgXH6.setImageResource(R.drawable.yellowstar);
            case 5:
                imgXH5.setImageResource(R.drawable.yellowstar);
            case 4:
                imgXH4.setImageResource(R.drawable.yellowstar);
            case 3:
                imgXH3.setImageResource(R.drawable.yellowstar);
            case 2:
                imgXH2.setImageResource(R.drawable.yellowstar);
            case 1:
                imgXH1.setImageResource(R.drawable.yellowstar);
        }
        switch (a) {
            case 0:
                imgXH1.setImageResource(R.drawable.blackstar);
            case 1:
                imgXH2.setImageResource(R.drawable.blackstar);
            case 2:
                imgXH3.setImageResource(R.drawable.blackstar);
            case 3:
                imgXH4.setImageResource(R.drawable.blackstar);
            case 4:
                imgXH5.setImageResource(R.drawable.blackstar);
            case 5:
                imgXH6.setImageResource(R.drawable.blackstar);
            case 6:
                imgXH7.setImageResource(R.drawable.blackstar);
            case 7:
                imgXH8.setImageResource(R.drawable.blackstar);
            case 8:
                imgXH9.setImageResource(R.drawable.blackstar);
            case 9:
                imgXH10.setImageResource(R.drawable.blackstar);

        }
    }

    private class GetDanhGia extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURLDanhGia);
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            if (IDUser.idUser < 0) {

            } else {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");


                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String Diem = jsonObject1.getString("Diem");

                    txtDanhGiaNguoiDung.setText("Bạn đã đánh giá phim này " + Diem + " Điểm");
                    txtXoaDanhGia.setVisibility(View.VISIBLE);

                } catch (JSONException e) {

                }
            }
        }
    }

    private class DangDanhGia extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    if (txtDanhGiaNguoiDung.getText().toString().equals("")) {
                        jsonURLDangDanhGia = InsertDanhGia + trangChiTiet.getIdPhim().toString() + "/" + IDUser.idUser.toString() + "/" + date + "/" + time + "/" + Diem;
                        url = new URL(jsonURLDangDanhGia);
                    } else {
                        jsonURLDangDanhGia = UpdateDanhGia + date + "/" + time + "/" + Diem + "/" + trangChiTiet.getIdPhim().toString() + "/" + IDUser.idUser.toString();
                        url = new URL(jsonURLDangDanhGia);
                    }
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            GetDiem getDiem = new GetDiem();
            getDiem.execute();
        }
    }

    private class XoaDanhGia extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {

                        jsonURLDangDanhGia = XoaDanhGia + trangChiTiet.getIdPhim().toString() + "/" + IDUser.idUser.toString();
                        url = new URL(jsonURLDangDanhGia);

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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            txtDanhGiaNguoiDung.setText("");
            txtXoaDanhGia.setVisibility(View.INVISIBLE);
            GetDanhGia getDanhGia = new GetDanhGia();
            getDanhGia.execute();
            GetDiem getDiem = new GetDiem();
            getDiem.execute();
        }
    }

    private class GetDiem extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {

                    DiemTheoPhim = URLDiem + trangChiTiet.getIdPhim().toString();
                    url = new URL(DiemTheoPhim);

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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");


                JSONObject jsonObject1 = jsonArray.getJSONObject(0);

                Double Diem = jsonObject1.getDouble("Diem");


                txtDiem.setText(Diem.toString());

                int x = 0;
                if (Double.parseDouble(txtDiem.getText().toString()) < 10) {
                    x = Integer.parseInt(txtDiem.getText().toString().substring(0, 1));
                } else {
                    x = Integer.parseInt(txtDiem.getText().toString().substring(0, 2));
                }

                DanhGiaDiem(x);




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
