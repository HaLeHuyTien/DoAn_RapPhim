package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_rapphim.R;

public class ThongTinFragment extends Fragment {
    private Button btnDatVe;
    public static ThongTinFragment getInstance(){
        ThongTinFragment thongTinFragment = new ThongTinFragment();
        return  thongTinFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin, container,false);
        btnDatVe = view.findViewById(R.id.btnDatVe);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.doan_rapphim.packageThanhToan.ThanhToan.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
