package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;

import java.util.LinkedList;
import java.util.PrimitiveIterator;

public class BinhLuanFragment extends Fragment {

    private Button btn;
    //RecyclerView
    private final LinkedList<BinhLuan_Json> mWordList = new LinkedList<>();
    private RecyclerView recyclerView;
    private BinhLuanListAdapter adapter;

    public static BinhLuanFragment getInstance(){
        BinhLuanFragment binhLuanFragment = new BinhLuanFragment();
        return  binhLuanFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.binhluan, container,false);
        recyclerView = view.findViewById(R.id.recycler_view_binh_luan);
        btn = view.findViewById(R.id.btnDangBinhLuan);
        HiennThiDanhSach();
        return view;

    }

    public void HiennThiDanhSach(){
        try {
            Integer soluongBinhLuan =ReadBinhLuanJson.SoLuongBinhLuan(getActivity());
            mWordList.clear();
            for(Integer i = 0; i < soluongBinhLuan; i++){
                BinhLuan_Json binhLuan_json = ReadBinhLuanJson.readBinhLuanJsonFile(getActivity(),i);
                mWordList.addLast(binhLuan_json);
            }
            adapter = new BinhLuanListAdapter(getActivity(),mWordList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }catch (Exception e){
            btn.setText("Hello");
        }

    }
}
