package com.example.doan_rapphim;

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

import com.example.doan_rapphim.packageTrangChiTiet.ReadBinhLuanJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link timPhimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class timPhimFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private final LinkedList<Phim> mWordList=new LinkedList<>();
    private RecyclerView mRecyclerview;
    private AdapterListPhimItem mAdapter;
    private EditText txt;
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

        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }

       try {
           LinkedList<Phim> result=new LinkedList<>();
           Integer soluongphim = readJsonListPhim.SoLuongPhim(getActivity());
           mWordList.clear();

           for(Integer i = 0; i < soluongphim; i++){
                Phim phim=readJsonListPhim.readPhimJson(getActivity(),i);
                mWordList.addLast(phim);
            }



           mRecyclerview=view.findViewById(R.id.recylerview);

           mAdapter=new AdapterListPhimItem(getActivity(),mWordList);

           mRecyclerview.setAdapter(mAdapter);

           mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        }catch (Exception e){
            Toast.makeText(getActivity(),"sai",Toast.LENGTH_LONG).show();
        }

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<Phim> timkiem =new LinkedList<>();
                EditText editText=view.findViewById(R.id.txtTim);
                try {

                    Integer soluongphim = readJsonListPhim.SoLuongPhim(getActivity());
                    mWordList.clear();

                    for(Integer i = 0; i < soluongphim; i++){
                        Phim phim=readJsonListPhim.readPhimJson(getActivity(),i);
                        if(phim.getTenPhim().equals(editText.getText().toString()))
                            mWordList.addLast(phim);
                    }


                    mAdapter=new AdapterListPhimItem(getActivity(),mWordList);

                    mRecyclerview.setAdapter(mAdapter);

                    mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


                }catch (Exception e){
                    Toast.makeText(getActivity(),"sai",Toast.LENGTH_LONG).show();
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s=parent.getItemAtPosition(position).toString();
        if(s.equals("Phim Đang Chiếu"))
            Trangthai="dangchieu";
        else
            Trangthai="sapchieu";

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
}