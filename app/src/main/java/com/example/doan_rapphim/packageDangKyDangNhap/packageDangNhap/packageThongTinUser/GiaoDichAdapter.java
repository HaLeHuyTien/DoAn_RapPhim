package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.BinhLuanListAdapter;

import java.util.LinkedList;

public class GiaoDichAdapter extends RecyclerView.Adapter<GiaoDichAdapter.GiaoDichViewHolder>{
    private final LinkedList<ThongTinGiaoDich_Json> mGiaoDichList;
    private LayoutInflater layoutInflater;
    private Context context;

    public GiaoDichAdapter(Context context, LinkedList<ThongTinGiaoDich_Json> wordList){
        layoutInflater = LayoutInflater.from(context);
        this.mGiaoDichList = wordList;
        this.context = context;
    }
    @NonNull
    @Override
    public GiaoDichAdapter.GiaoDichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.listlichsudatve_item,parent,false);
        return new GiaoDichViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull GiaoDichAdapter.GiaoDichViewHolder holder, int position) {
        ThongTinGiaoDich_Json thongTinGiaoDich_json = mGiaoDichList.get(position);holder.txtTenPhim.setText(thongTinGiaoDich_json.getTenPhim());
        holder.txtTenRap.setText(thongTinGiaoDich_json.getTenRap());
        holder.txtGhe.setText(thongTinGiaoDich_json.getGhe());
        holder.txtNgayDat.setText(thongTinGiaoDich_json.getNgayDatVe());
        holder.txtTongTien.setText(thongTinGiaoDich_json.getTongTien());
    }

    @Override
    public int getItemCount() {
        return mGiaoDichList.size();
    }

    public class GiaoDichViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTenRap;
        public TextView txtTenPhim;
        public TextView txtGhe;
        public TextView txtNgayDat;
        public TextView txtTongTien;

        final GiaoDichAdapter giaoDichAdapter;
        public GiaoDichViewHolder(View mItemView, GiaoDichAdapter giaoDichAdapter) {
            super(mItemView);
            txtTenPhim = mItemView.findViewById(R.id.txtTenPhim);
            txtTenRap = mItemView.findViewById(R.id.txtTenRap);
            txtGhe= mItemView.findViewById(R.id.txtGhe);
            txtNgayDat = mItemView.findViewById(R.id.txtNgayDat);
            txtTongTien = mItemView.findViewById(R.id.txtTongTien);
            this.giaoDichAdapter =giaoDichAdapter;

        }
    }
}
