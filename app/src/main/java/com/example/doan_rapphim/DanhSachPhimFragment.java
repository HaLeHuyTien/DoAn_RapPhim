package com.example.doan_rapphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class DanhSachPhimFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager mviewPager;
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
