package com.example.doan_rapphim;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan_rapphim.packageTrangChiTiet.BinhLuanListAdapter;
import com.example.doan_rapphim.packageTrangChiTiet.BinhLuan_Json;
import com.example.doan_rapphim.packageTrangChiTiet.ReadBinhLuanJson;
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
 * Use the {@link timPhimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class timPhimFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private  String jsonURLDangChieu= "http://0306181355.pixelcent.com/Cinema/PhimDangChieu.php";
    private  String jsonURLSapChieu= "http://0306181355.pixelcent.com/Cinema/PhimSapChieu.php";
    private final LinkedList<ThongTinJson> mWordList=new LinkedList<>();
    private RecyclerView mRecyclerview;
    private AdapterListPhimItem mAdapter;
    private EditText txtTim;
    public String Trangthai;
    private Button btnTimKiem;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public timPhimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment timPhimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static timPhimFragment newInstance(String param1, String param2) {
        timPhimFragment fragment = new timPhimFragment();
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

        View view = inflater.inflate(R.layout.fragment_tim_phim,container,false);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(),R.array.lables_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner=view.findViewById(R.id.spinner);
        btnTimKiem = view.findViewById(R.id.btnTim);
        mRecyclerview=view.findViewById(R.id.recylerview);
        txtTim = view.findViewById(R.id.txtTim);

        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }

       GetPhim getPhim = new GetPhim();
        getPhim.execute();

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPhim getPhim1 = new GetPhim();
                getPhim1.execute();

            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    void HienthiDanhSach(View view)
    {
        try {
            LinkedList<ThongTinJson> result=new LinkedList<>();
            Integer soluongphim = ReadThongTinJson.SoLuongPhim(getActivity());
            mWordList.clear();

            for(Integer i = 0; i < soluongphim; i++){
                ThongTinJson thongTinJson = ReadThongTinJson.readThongTinJsonFile(getActivity(),i);
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
                    if(Trangthai.equals("dangchieu"))
                    url = new URL(jsonURLDangChieu);
                    else
                        url = new URL(jsonURLSapChieu);
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

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String id = jsonObject1.getString("id");
                    String TenPhim = jsonObject1.getString("TenPhim");
                    String LoaiPhim = jsonObject1.getString("LoaiPhim");
                    String DaoDien = jsonObject1.getString("DaoDien");
                    String GioiHanTuoi = jsonObject1.getString("GioiHanTuoi");
                    String Hinh = jsonObject1.getString("Hinh");
                    String NgayKhoiChieu = jsonObject1.getString("NgayKhoiChieu");
                    Integer a = Integer.parseInt(id);

                    ThongTinJson Phim = new ThongTinJson();
                    Phim.setIDPhim(a);
                    Phim.setTenPhim(TenPhim);
                    Phim.setTheLoai(LoaiPhim);
                    Phim.setDaoDien(DaoDien);
                    Phim.setDoTuoi(GioiHanTuoi);
                    Phim.setHinhPhim(Hinh);
                    Phim.setNgayKhoiChieu(NgayKhoiChieu);


                    try {
//                        SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy");
//                        Date strDate = sdf.parse(Phim.getNgayKhoiChieu());
//                        String currentTime = sdf.format(Calendar.getInstance().getTime());
//
//
//                        Date currentDay = sdf.parse(currentTime);

                            //if (strDate.before(currentDay) || currentTime.equals(Phim.getNgayKhoiChieu()))
                            //if (strDate.after(currentDay))
                                if(txtTim.getText().toString().replace(" ","").equals("") || Phim.getTenPhim().toLowerCase().indexOf(txtTim.getText().toString().toLowerCase()) > -1)
                                mWordList.addLast(Phim);

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                mAdapter = new AdapterListPhimItem(getContext(),getActivity(),mWordList);
                mRecyclerview.setAdapter(mAdapter);
                mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s=parent.getItemAtPosition(position).toString();
        if(s.equals("Phim Đang Chiếu")) {
            Trangthai = "dangchieu";
            GetPhim getPhim = new GetPhim();
            getPhim.execute();
        }

        else {
            Trangthai = "sapchieu";
            GetPhim getPhim = new GetPhim();
            getPhim.execute();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
}