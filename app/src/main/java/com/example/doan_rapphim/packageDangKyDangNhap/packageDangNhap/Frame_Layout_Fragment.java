package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TabTaiKhoan_Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frame_Layout_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frame_Layout_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frame_Layout_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frame_Layout_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Frame_Layout_Fragment newInstance(String param1, String param2) {
        Frame_Layout_Fragment fragment = new Frame_Layout_Fragment();
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
        if(IDUser.idUser < 0) {
        KhoiTao();
        }
        else {
            KhoiTao2();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frame__layout_, container, false);
    }

    private void KhoiTao() {


            DangNhapFragment firstFragment = new DangNhapFragment();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.container_body, firstFragment);

            ft.commit();


    }
    private void KhoiTao2() {


        TabTaiKhoan_Fragment firstFragment = new TabTaiKhoan_Fragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.container_body, firstFragment);

        ft.commit();


    }
}