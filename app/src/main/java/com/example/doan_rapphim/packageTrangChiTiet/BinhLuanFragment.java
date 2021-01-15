package com.example.doan_rapphim.packageTrangChiTiet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
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

public class BinhLuanFragment extends Fragment {

    private String jsonURLBL;
    private final String value = "http://0306181355.pixelcent.com/rapphim/public/api/BinhLuanTheoPhim/";
    private String jsonURLDangBL;
    private final String valueDangBL = "http://0306181355.pixelcent.com/rapphim/public/api/DangBinhLuan/";
    private EditText txtNDBinhLuan;
    private Button btnBinhLuan;
    private TextView txtThongBaoBL;
    private ImageView imgNguoiDangBinhLuan;
    private final LinkedList<BinhLuan_Json> mWordList = new LinkedList<>();
    private RecyclerView recyclerView;
    private BinhLuanListAdapter adapter;
    private TrangChiTiet mtrangChiTiet;


    public static BinhLuanFragment getInstance() {
        BinhLuanFragment binhLuanFragment = new BinhLuanFragment();
        return binhLuanFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.binhluan, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_binh_luan);
        btnBinhLuan = view.findViewById(R.id.btnDangBinhLuan);
        txtNDBinhLuan = view.findViewById(R.id.editTextBinhLuan);
        txtThongBaoBL = view.findViewById(R.id.txtThongBaoBL);
        imgNguoiDangBinhLuan = view.findViewById(R.id.imgNguoiDangBinhLuan);
        mtrangChiTiet = (TrangChiTiet) getActivity();
        jsonURLBL = value + mtrangChiTiet.getIdPhim().toString();
        if (IDUser.idUser < 0) {
            txtNDBinhLuan.setEnabled(false);
            txtNDBinhLuan.setHint("Đăng nhập để bình luận phim !!");
        } else {
            String a = IDUser.HinhUser;
            Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + IDUser.HinhUser).into(imgNguoiDangBinhLuan);
        }


        GetBinhLuan getBinhLuan = new GetBinhLuan();
        getBinhLuan.execute();

        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IDUser.idUser < 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    // Set a title for alert dialog
                    builder.setTitle("Thông Báo");

                    // Ask the final question
                    builder.setMessage("Vui lòng đăng nhập để bình luận phim");

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

                    if (txtNDBinhLuan.getText().toString().replace(" ", "").equals("")) {
                        txtThongBaoBL.setText("Bạn phải nhập bình luận");
                    } else {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-d");
                        String date = df.format(Calendar.getInstance().getTime());
                        DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
                        String time = df2.format(Calendar.getInstance().getTime());
                        jsonURLDangBL = valueDangBL + IDUser.idUser + "/" + mtrangChiTiet.getIdPhim() + "/" + date + "/" + time + "/" + txtNDBinhLuan.getText().toString();
                        DangBinhLuan dangBinhLuan = new DangBinhLuan();
                        dangBinhLuan.execute();
                        GetBinhLuan getBinhLuan1 = new GetBinhLuan();
                        getBinhLuan1.execute();
                        txtThongBaoBL.setText("");
                        txtNDBinhLuan.setText("");
                    }
                }
            }
        });

        return view;

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

            } catch (Exception e) {
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

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String HoTen = jsonObject1.getString("HoTen");
                    String NoiDung = jsonObject1.getString("NoiDung");
                    String Ngay = jsonObject1.getString("Ngay");
                    String Gio = jsonObject1.getString("Gio");
                    String Hinh = jsonObject1.getString("Hinh");

                    BinhLuan_Json binhLuan_json = new BinhLuan_Json();
                    binhLuan_json.setNgayBinhLuan(Ngay);
                    binhLuan_json.setAnhNguoiBinhLuan(Hinh);
                    binhLuan_json.setTenNguoiBinhLuan(HoTen);
                    binhLuan_json.setNoiDungBinhLuan(NoiDung);
                    binhLuan_json.setGioBinhLuan(Gio);

                    mWordList.addLast(binhLuan_json);
                }
                adapter = new BinhLuanListAdapter(getActivity(), mWordList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private class DangBinhLuan extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURLDangBL);
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

        }
    }
}
