package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_rapphim.R;

public class BinhLuanFragment extends Fragment {

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
        return view;

    }
}
