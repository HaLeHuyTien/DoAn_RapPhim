package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_rapphim.AdapterListPhimItem;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinFragment;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TaiKhoan_ThongTinFragment extends Fragment {



    private Button ThayDoiThongTin;
    private Button ThayDoiMatKhau;


    private ImageView txtanhDaiDien;
    private TextView txtHoVaTen;
    private TextView txtEmail;
    private TextView txtSDT;
    private TextView txtNgaySinh;
    private TextView txtDiaChi;
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
    private static String jsonURL;
    private String iduser = "http://0306181355.pixelcent.com/Cinema/ThongTinKhachHang.php?ID=";
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


        //HienThiUser();
        // Inflate the layout for this fragment
        ThayDoiThongTin = view.findViewById(R.id.btnThayDoiTT);
        ThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiThongTin.class);
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

        jsonURL = iduser + IDUser.idUser.toString();
        GetThongTinKH getThongTinKH = new GetThongTinKH();
        getThongTinKH.execute();
        return view;
    }

    private void HienThiUser() {
        try {

            //for(int i = 0; i < soluong ; i ++) {
                ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(getActivity(), IDUser.idUser);
                //Truyen Id if(thongTinUser.getID() == )
                txtEmail.setText(thongTinUser.getEmail());
                int resID = this.getContext().getResources().getIdentifier(thongTinUser.getAnh(),"drawable",this.getContext().getPackageName());
                txtanhDaiDien.setImageResource(resID);
                txtHoVaTen.setText(thongTinUser.getHoVaTen());
                txtSDT.setText(thongTinUser.getSDT());
                txtNgaySinh.setText(thongTinUser.getNgaySinh());
                txtDiaChi.setText(thongTinUser.getXaPhuong()+", " + thongTinUser.getHuyenQuan()+", "+thongTinUser.getTinhTP());
            //}
        }catch (Exception e){
            txtEmail.setText("Error");
        }

    }

    private class GetThongTinKH extends AsyncTask<String, String, String> {


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

                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    Integer Id = jsonObject1.getInt("ID");
                    String HVT = jsonObject1.getString("HoVaTen");
                    String Email = jsonObject1.getString("Email");
                    String SDT = jsonObject1.getString("SDT");
                    String NgaySinh = jsonObject1.getString("NgaySinh");
                    String XaPhuong = jsonObject1.getString("Phuong");
                    String HuyenQuan = jsonObject1.getString("Quan");
                    String TinhThanhPho = jsonObject1.getString("ThanhPho");
                    //String MatKhau = jsonObject1.getString("MatKhau");
                    String Anh = jsonObject1.getString("Anh");
                    Integer TrangThai = jsonObject1.getInt("TrangThai");

                txtEmail.setText(Email);
                int resID = getContext().getResources().getIdentifier(Anh,"drawable",getContext().getPackageName());
                txtanhDaiDien.setImageResource(resID);
                txtHoVaTen.setText(HVT);
                txtSDT.setText(SDT);
                txtNgaySinh.setText(NgaySinh);
                txtDiaChi.setText(XaPhuong+", " + HuyenQuan+", "+ TinhThanhPho);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}