package com.example.doan_rapphim.packageDanhSachPhim;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doan_rapphim.AdapterListPhimItem;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.ReadThongTinJson;
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
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DSPhimSapChieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DSPhimSapChieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DSPhimSapChieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DSPhimSapChieu.
     */
    // TODO: Rename and change types and number of parameters
    public static DSPhimSapChieu newInstance(String param1, String param2) {
        DSPhimSapChieu fragment = new DSPhimSapChieu();
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

    private final LinkedList<ThongTinJson> mWordList=new LinkedList<>();
    private RecyclerView mRecyclerview;
    private AdapterListPhimItem mAdapter;

    private static String jsonURL = "http://0306181355.pixelcent.com/Cinema/Phim.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_s_phim_sap_chieu, container, false);
        mRecyclerview=view.findViewById(R.id.RVDSPhimSapChieu);
        GetPhim getPhim = new GetPhim();
        getPhim.execute();
        //HienthiDanhSach(view);
        return  view;
    }

    void HienthiDanhSach(View view)
    {
        try {
            LinkedList<ThongTinJson> result=new LinkedList<>();
            Integer soluongphim = ReadThongTinJson.SoLuongPhim(getActivity());
            mWordList.clear();

            for(Integer i = 0; i < soluongphim; i++){
                ThongTinJson thongTinJson = ReadThongTinJson.readThongTinJsonFile(getActivity(),i);


                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date strDate = sdf.parse(thongTinJson.getNgayKhoiChieu());
                String currentTime = sdf.format(Calendar.getInstance().getTime());
                Date currentDay = sdf.parse(currentTime);
                if(strDate.after(currentDay))
                    mWordList.addLast(thongTinJson);
            }



            mAdapter=new AdapterListPhimItem(getContext(),getActivity(),mWordList);


            mRecyclerview.setAdapter(mAdapter);

            mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        }catch (Exception e){
            Toast.makeText(getActivity(),"sai",Toast.LENGTH_LONG).show();
        }
    }

    private class GetPhim extends AsyncTask<String, String, String> {


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

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
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
                    Integer a = Integer.parseInt(id);

                    ThongTinJson Phim = new ThongTinJson();
                    Phim.setIDPhim(a);
                    Phim.setTenPhim(TenPhim);
                    Phim.setTheLoai(LoaiPhim);
                    Phim.setDaoDien(DaoDien);
                    Phim.setHinhDaoDien(HinhDaoDien);
                    Phim.setThoiLuong(ThoiLuong);
                    Phim.setDoTuoi(GioiHanTuoi);
                    Phim.setTrailer(VideoTrailer);
                    Phim.setTomTat(NoiDung);
                    Phim.setHinhPhim(Hinh);
                    Phim.setDiem(9.0);
                    Phim.setNgayKhoiChieu("30/11/2020");
                    Phim.setNhaSanXuat(NhaSanXuat);


                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date strDate = sdf.parse(Phim.getNgayKhoiChieu());
                        String currentTime = sdf.format(Calendar.getInstance().getTime());
                        Date currentDay = sdf.parse(currentTime);
                        if(strDate.after(currentDay))
                            mWordList.addLast(Phim);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

                mAdapter=new AdapterListPhimItem(getContext(),getActivity(),mWordList);

                mRecyclerview.setAdapter(mAdapter);

                mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}