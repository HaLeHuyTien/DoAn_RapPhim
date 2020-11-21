package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.doan_rapphim.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.LinkedList;

public class ThongTinFragment extends Fragment {

    private ImageView imgHinhPhim;
    private  TextView txtTenPhim;
    private ReadMoreTextView txtTomTat;
    private TextView txtNgayKhoiChieu;
    private TextView txtThoiLuong;
    private TextView txtTheLoai;
    private TextView txtNgonNgu;
    private ImageView imgXH1;
    private ImageView imgXH2;
    private ImageView imgXH3;
    private ImageView imgXH4;
    private ImageView imgXH5;
    private TextView txtDiem;
    private TextView txtDoTuoi;
    private YouTubePlayerView ytbTrailer;

    private final LinkedList<DienVienJson> mWordList = new LinkedList<>();
    private RecyclerView rvDienVien;
    private DienVienListAdapter adapter;


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

        //TrangChiTiet
        imgHinhPhim = view.findViewById(R.id.imgHinhPhim);
        txtTenPhim = view.findViewById(R.id.txtTenPhim);
        txtTomTat = view.findViewById(R.id.txtTomTat);
        txtNgayKhoiChieu = view.findViewById(R.id.txtNgayKhoiChieu);
        txtThoiLuong = view.findViewById(R.id.txtThoiLuong);
        txtTheLoai = view.findViewById(R.id.txtTheLoai);
        txtNgonNgu = view.findViewById(R.id.txtNgonNgu);
        imgXH1 = view.findViewById(R.id.imgXH1);
        imgXH2 = view.findViewById(R.id.imgXH2);
        imgXH3 = view.findViewById(R.id.imgXH3);
        imgXH4 = view.findViewById(R.id.imgXH4);
        imgXH5 = view.findViewById(R.id.imgXH5);
        txtDiem = view.findViewById(R.id.txtDiem);
        txtDoTuoi = view.findViewById(R.id.txtDoTuoi);
        ytbTrailer = view.findViewById(R.id.youtube_player_view);

        rvDienVien = view.findViewById(R.id.rvDienVien);

        //Gan vao` Trang Chi Tiet
        try {
            ThongTinJson thongTinJson = ReadThongTinJson.readThongTinJsonFile(getActivity(),0);
            int resId = this.getContext().getResources().getIdentifier(thongTinJson.getHinhPhim(),"drawable",getContext().getPackageName());
            imgHinhPhim.setImageResource(resId);
            txtTenPhim.setText(thongTinJson.getTenPhim());
            txtTomTat.setText(thongTinJson.getTomTat());
            txtNgayKhoiChieu.setText(thongTinJson.getNgayKhoiChieu());
            txtThoiLuong.setText(thongTinJson.getThoiLuong());
            txtTheLoai.setText(thongTinJson.getTheLoai());
            txtNgonNgu.setText(thongTinJson.getNgonNgu());
            txtDiem.setText(thongTinJson.getDiem().toString());
            txtDoTuoi.setText(thongTinJson.getDoTuoi());
            ytbTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(thongTinJson.getTrailer(),0);
                    youTubePlayer.pause();
                }


            });
            int soluongDV = ReadDienVienJson.SoLuongDienVien(getActivity(),thongTinJson.getIDPhim());
            for (int i = 0; i< soluongDV ; i++) {
                DienVienJson dienVienJson = ReadDienVienJson.readDienVienJsonFile(getActivity(), thongTinJson.getIDPhim(),i);
                mWordList.addLast(dienVienJson);
            }
            adapter = new DienVienListAdapter(getActivity(),mWordList);
           rvDienVien.setAdapter(adapter);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
           rvDienVien.setLayoutManager(manager);
        }catch (Exception e)
        {
            txtTenPhim.setText("Lỗi");
        }



        btnDatVe = view.findViewById(R.id.btnDatVe);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThanhToan.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
