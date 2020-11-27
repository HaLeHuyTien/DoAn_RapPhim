package com.example.doan_rapphim.packageDanhSachPhim;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan_rapphim.AdapterListPhimItem;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.IDPhim;
import com.example.doan_rapphim.packageTrangChiTiet.ReadThongTinJson;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinJson;
import com.example.doan_rapphim.packageTrangChiTiet.TrangChiTiet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DSPhimDangChieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DSPhimDangChieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DSPhimDangChieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DSPhimDangChieu.
     */
    // TODO: Rename and change types and number of parameters
    public static DSPhimDangChieu newInstance(String param1, String param2) {
        DSPhimDangChieu fragment = new DSPhimDangChieu();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_s_phim_dang_chieu, container, false);
        mRecyclerview=view.findViewById(R.id.RVDSPhimDangChieu);
        HienthiDanhSach(view);


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

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                Date strDate = sdf.parse(thongTinJson.getNgayKhoiChieu());
                String currentTime = sdf.format(Calendar.getInstance().getTime());
                Date currentDay = sdf.parse(currentTime);
                if(strDate.before(currentDay))
                    mWordList.addLast(thongTinJson);


            }





            mAdapter=new AdapterListPhimItem(getContext(),getActivity(),mWordList);


            mRecyclerview.setAdapter(mAdapter);

            mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        }catch (Exception e){
            Toast.makeText(getActivity(),"sai",Toast.LENGTH_LONG).show();
        }
    }

}