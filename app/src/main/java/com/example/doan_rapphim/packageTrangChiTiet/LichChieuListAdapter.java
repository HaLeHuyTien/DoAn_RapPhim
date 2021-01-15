package com.example.doan_rapphim.packageTrangChiTiet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.SoDoGhe;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThongTinSoDoGhe;

import java.util.LinkedList;

public class LichChieuListAdapter extends RecyclerView.Adapter<LichChieuListAdapter.LichChieuViewHolder> {
    private final LinkedList<LichChieu_Json> mLichChieuList;
    private final LayoutInflater mInflater;

    private final Activity activity;


    public LichChieuListAdapter(Context context, Activity activity, LinkedList<LichChieu_Json> wordList) {
        mInflater = LayoutInflater.from(context);
        this.activity = activity;
        this.mLichChieuList = wordList;
    }

    @NonNull
    @Override
    public LichChieuListAdapter.LichChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_lichchieu, parent, false);
        return new LichChieuViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LichChieuListAdapter.LichChieuViewHolder holder, int position) {
        LichChieu_Json mCurrent = mLichChieuList.get(position);
        holder.txtTenRap.setText(mCurrent.getTenRap());
        String[] a = mCurrent.getXuatChieu();
        for (int i = 0; i < a.length; i++) {
            String[] XuatChieu = a[i].split("/");
            holder.btnGioChieu[i].setText(XuatChieu[0]);
            int value = i;
            holder.btnGioChieu[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThongTinSoDoGhe.suatChieu = XuatChieu[0].toString();
                    ThongTinSoDoGhe.IDPhong = mCurrent.getIDPhong();
                    ThongTinSoDoGhe.TenPhong = XuatChieu[1];
                    ThongTinSoDoGhe.IDRap = mCurrent.getIDRap();
                    switch (v.getId()) {
                        case R.id.btnGioChieu1:
                            ThongTinSoDoGhe.IDXuatChieu = 1;
                            break;
                        case R.id.btnGioChieu2:
                            ThongTinSoDoGhe.IDXuatChieu = 2;
                            break;
                        case R.id.btnGioChieu3:
                            ThongTinSoDoGhe.IDXuatChieu = 3;
                            break;
                        case R.id.btnGioChieu4:
                            ThongTinSoDoGhe.IDXuatChieu = 4;
                            break;
                        case R.id.btnGioChieu5:
                            ThongTinSoDoGhe.IDXuatChieu = 5;
                            break;
                        case R.id.btnGioChieu6:
                            ThongTinSoDoGhe.IDXuatChieu = 6;
                            break;
                    }
                    Intent intent = new Intent(activity, SoDoGhe.class);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mLichChieuList.size();
    }

    public class LichChieuViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTenRap;
        public final Button[] btnGioChieu = new Button[6];
        final LichChieuListAdapter mAdapter;

        public LichChieuViewHolder(View itemView, LichChieuListAdapter adapter) {
            super(itemView);
            txtTenRap = itemView.findViewById(R.id.txtTenRap);
            btnGioChieu[0] = itemView.findViewById(R.id.btnGioChieu1);
            btnGioChieu[1] = itemView.findViewById(R.id.btnGioChieu2);
            btnGioChieu[2] = itemView.findViewById(R.id.btnGioChieu3);
            btnGioChieu[3] = itemView.findViewById(R.id.btnGioChieu4);
            btnGioChieu[4] = itemView.findViewById(R.id.btnGioChieu5);
            btnGioChieu[5] = itemView.findViewById(R.id.btnGioChieu6);
            this.mAdapter = adapter;
        }
    }


}
