package com.example.doan_rapphim.packageDangKyDangNhap;

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
import com.example.doan_rapphim.packageThongTinUser.TabTaiKhoan;

public class UserFragment extends Fragment {
private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        button=view.findViewById(R.id.btnDK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity() ,DangKyActiviti.class);
                startActivity(intent);
            }
        });

        button=view.findViewById(R.id.btnDN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity() , TabTaiKhoan.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
