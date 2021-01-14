package com.example.doan_rapphim;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class TrangChuFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Photo_Slide_Adapter photo_slide_adapter;
    private List<Photo> photoList;
    private Timer timer;

    private TabLayout tabLayout;
    private ViewPager mviewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu,container,false);


        tabLayout=view.findViewById(R.id.tablayout);
        mviewPager=view.findViewById(R.id.trangchu_viewpager);

        TrangChuDSphim_Adapter trangChuDSphim_adapter = new TrangChuDSphim_Adapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(trangChuDSphim_adapter);
        tabLayout.setupWithViewPager(mviewPager);




        return view;
    }



}
