package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.DangNhapFragment;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinFragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TaiKhoan_ThongTinFragment extends Fragment {

    private Button ThayDoiThongTin;
    private Button ThayDoiMatKhau;
    private Button DangXuat;

    private ImageView txtanhDaiDien;
    private TextView txtHoVaTen;
    private TextView txtEmail;
    private TextView txtSDT;
    private TextView txtNgaySinh;
    private TextView txtDiaChi;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ThongTinUser getInstance() {
        ThongTinUser thongTinUser = new ThongTinUser();
        return thongTinUser;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongTinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongTinFragment newInstance(String param1, String param2) {
        ThongTinFragment fragment = new ThongTinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public static String jsonURL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        //mRecyclerView = view.findViewById(R.id.recycler_view_thongtin);
        txtanhDaiDien = view.findViewById(R.id.imgHinhDaiDienTT1);
        txtHoVaTen = view.findViewById(R.id.txtHoVaTenTT);
        txtEmail = view.findViewById(R.id.txtEmailTT);
        txtSDT = view.findViewById(R.id.txtSDTTT);
        txtNgaySinh = view.findViewById(R.id.txtNgaySinhTT);
        txtDiaChi = view.findViewById(R.id.txtDiaChiTT);


        ThayDoiThongTin = view.findViewById(R.id.btnThayDoiTT);
        ThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongTinContext.context = getContext();
                Intent intent = new Intent(getContext(), com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.XacThucMatKhauActivity.class);
                startActivity(intent);

            }
        });
        ThayDoiMatKhau = view.findViewById(R.id.btnThayDoiMK);
        ThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiMatKhau.class);
                startActivity(intent);
            }
        });

        initPreferences();

        DangXuat = view.findViewById(R.id.btnDangXuat);
        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông Báo");

                // Ask the final question
                builder.setMessage("Đồng ý Đăng xuất ?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        editor.putInt("DATA1", -1);
                        editor.putString("DATA2", "dienvien");
                        IDUser.idUser = -1;

                        editor.commit();
                        replaceFragmentContent(new DangNhapFragment());
                        Toast.makeText(getContext(), "Đăng xuất thành công !", Toast.LENGTH_LONG).show();
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

        GetThongTinKH getThongTinKH = new GetThongTinKH();
        getThongTinKH.execute();
        return view;
    }

    private class GetThongTinKH extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    jsonURL = "http://0306181355.pixelcent.com/rapphim/public/api/ThongTinKhachHang/" + IDUser.idUser.toString();
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
                Integer id = jsonObject1.getInt("id");

                String HoTen = jsonObject1.getString("HoTen");
                String Email = jsonObject1.getString("Email");
                String SDT = jsonObject1.getString("SDT");
                String NgaySinh = jsonObject1.getString("NgaySinh");
                String DiaChi = jsonObject1.getString("DiaChi");
                String MatKhau = jsonObject1.getString("MatKhau");
                String Hinh = jsonObject1.getString("Hinh");
                Integer TrangThai = jsonObject1.getInt("TrangThai");


                txtEmail.setText(Email);
                txtanhDaiDien.setImageResource(0);
                Glide.with(getActivity()).load("http://0306181355.pixelcent.com/rapphim/public/images/" + Hinh).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(txtanhDaiDien);
                //Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + Hinh).into(txtanhDaiDien);

                txtHoVaTen.setText(HoTen);
                txtSDT.setText(SDT);
                txtNgaySinh.setText(NgaySinh);
                txtDiaChi.setText(DiaChi);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getActivity().getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.container_body, fragment);

            ft.commit();

        }

    }


    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
    }

}