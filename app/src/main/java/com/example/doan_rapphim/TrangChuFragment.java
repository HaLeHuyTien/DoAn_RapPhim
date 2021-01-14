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
<<<<<<< Updated upstream
        View view = inflater.inflate(R.layout.fragment_trangchu,container,false);

=======
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        viewPager = view.findViewById(R.id.view_pager_slide);
        circleIndicator = view.findViewById(R.id.circle_slide);
>>>>>>> Stashed changes

        tabLayout = view.findViewById(R.id.tablayout);
        mviewPager = view.findViewById(R.id.trangchu_viewpager);

        TrangChuDSphim_Adapter trangChuDSphim_adapter = new TrangChuDSphim_Adapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(trangChuDSphim_adapter);
        tabLayout.setupWithViewPager(mviewPager);

<<<<<<< Updated upstream

=======
        photoList = getListPhoto();
        photo_slide_adapter = new Photo_Slide_Adapter(getActivity(), getListPhoto());
        viewPager.setAdapter(photo_slide_adapter);

        circleIndicator.setViewPager(viewPager);
        photo_slide_adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoImage();
        return view;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.poster1));
        list.add(new Photo(R.drawable.poster2));
        list.add(new Photo(R.drawable.poster3));
        list.add(new Photo(R.drawable.poster4));
        list.add(new Photo(R.drawable.poster5));
>>>>>>> Stashed changes


        return view;
    }

<<<<<<< Updated upstream

=======
    private void autoImage() {
        if (photoList == null || photoList.isEmpty() || viewPager == null)
            return;

        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }

                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
>>>>>>> Stashed changes

}
