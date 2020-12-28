package com.example.doan_rapphim;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_rapphim.packageDanhSachPhim.DSPhimAdapter;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinJson;
import com.google.android.material.tabs.TabLayout;

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

public class DanhSachPhimFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager mviewPager;
    private Spinner spinnerSC;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhsachphim,container,false);

        tabLayout=view.findViewById(R.id.DSTabDS);
        mviewPager=view.findViewById(R.id.DSviewPager);
        DSPhimAdapter dsPhimAdapter = new DSPhimAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(dsPhimAdapter);
        tabLayout.setupWithViewPager(mviewPager);

        return view;
    }


}
