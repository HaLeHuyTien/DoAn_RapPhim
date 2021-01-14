package com.example.doan_rapphim;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trangchu_phimdangchieufragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trangchu_phimdangchieufragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Photo_Slide_Adapter photo_slide_adapter;
    private List<Photo> photoList;
    List<Photo> list = new ArrayList<>();
    private Timer timer;
    //API
    private final LinkedList<ThongTinJson> mWordList=new LinkedList<>();
    private AdapterListPhimItem mAdapter;


    private static String jsonURL = "http://0306181355.pixelcent.com/rapphim/public/api/lide";

    private TabLayout tabLayout;
    private ViewPager mviewPager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public trangchu_phimdangchieufragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trangchu_phimdangchieufragment.
     */
    // TODO: Rename and change types and number of parameters
    public static trangchu_phimdangchieufragment newInstance(String param1, String param2) {
        trangchu_phimdangchieufragment fragment = new trangchu_phimdangchieufragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu_phimdangchieufragment,container,false);
        viewPager = view.findViewById(R.id.view_pager_slide);
        circleIndicator = view.findViewById(R.id.circle_slide);

        GetSlide getPhim=new GetSlide();
        getPhim.execute();


        autoImage();
        return  view;
    }

    private void autoImage(){
        if(list == null || list.isEmpty() || viewPager == null)
            return;

        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = list.size()-1;
                        if(currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else{
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
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
    //API
    private class GetSlide extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            if (isAdded()){
                mWordList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String TenPhim = jsonObject1.getString("TenPhim");
                        String LoaiPhim = jsonObject1.getString("TenLoai");
                        String Hinh = jsonObject1.getString("Hinh");
                        String Diem = jsonObject1.getString("Diem");
                        String Tuoi = jsonObject1.getString("GioiHanTuoi");

                        list.add(new Photo(Hinh,TenPhim,LoaiPhim,Diem+"/10", "C"+Tuoi));
                    }

                    photo_slide_adapter=new Photo_Slide_Adapter(getActivity(),list);
                    viewPager.setAdapter(photo_slide_adapter);

                    circleIndicator.setViewPager(viewPager);
                    photo_slide_adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
                    autoImage();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}