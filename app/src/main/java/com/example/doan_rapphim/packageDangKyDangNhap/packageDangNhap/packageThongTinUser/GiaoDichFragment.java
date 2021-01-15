package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GiaoDichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GiaoDichFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam1;
    private String mParam2;
    private Spinner spnThang;
    private Button btnLichSuDV;
    private TextView txtHVT;
    private TextView txtTTNam;
    private TextView txtTTThang;

    private String URLTongTienTN;
    private final String URLValueNam = "http://0306181355.pixelcent.com/rapphim/public/api/TongTienTheoNam/";
    private String URLTongTienTT;
    private final String URLValueThang = "http://0306181355.pixelcent.com/rapphim/public/api/TongTienTheoThang/";
    private Integer Thang = 0;

    public GiaoDichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GiaoDichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GiaoDichFragment newInstance(String param1, String param2) {
        GiaoDichFragment fragment = new GiaoDichFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giao_dich, container, false);
        spnThang = view.findViewById(R.id.spinnerThang);
        txtHVT = view.findViewById(R.id.txtHVTGD);
        txtTTNam = view.findViewById(R.id.txtTTNam);
        txtTTThang = view.findViewById(R.id.txtTTThang);
        btnLichSuDV = view.findViewById(R.id.btnLichSuDatVe);


        btnLichSuDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrangDSLSDatVe();
            }
        });

        chonThang();
        spnThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Thang = position + 1;
                txtTTThang.setText("0 đ");
                GetTongTienTheoThang getTongTienTheoThang = new GetTongTienTheoThang();
                getTongTienTheoThang.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        GetTongTienTheoNam getTongTienTheoNam = new GetTongTienTheoNam();
        getTongTienTheoNam.execute();
        return view;

    }

    //Chuyen trang Xem Lich su dat ve
    public void ChuyenTrangDSLSDatVe() {
        Intent intent = new Intent(getActivity(), DSLichSuDatVeActivity.class);
        startActivity(intent);
    }

    //List thangs
    public void chonThang() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add("T " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnThang.setAdapter(adapter);
    }

    //Lay danh sach Tong Tien theo nam
    private class GetTongTienTheoNam extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    URLTongTienTN = URLValueNam + IDUser.idUser;
                    url = new URL(URLTongTienTN);
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
                    String TongTien = jsonObject1.getString("TongTien");

                    Integer tongTien = Integer.parseInt(TongTien);

                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    String kq = currencyVN.format(tongTien);
                    txtTTNam.setText(kq);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    //Lay danh sach Tong Tien theo Thang
    private class GetTongTienTheoThang extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    URLTongTienTT = URLValueThang + IDUser.idUser + "/" + Thang;
                    url = new URL(URLTongTienTT);
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
                    String TongTien = jsonObject1.getString("TongTien");

                    Integer tongTien = Integer.parseInt(TongTien);

                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    String kq = currencyVN.format(tongTien);

                    txtTTThang.setText(kq);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}