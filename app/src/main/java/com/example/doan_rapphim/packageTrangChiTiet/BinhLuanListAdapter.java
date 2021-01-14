package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class BinhLuanListAdapter extends RecyclerView.Adapter<BinhLuanListAdapter.BinhLuanViewHolder> {
    private final LinkedList<BinhLuan_Json> mBinhLuanList;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public BinhLuanListAdapter(Context context, LinkedList<BinhLuan_Json> wordList) {
        layoutInflater = LayoutInflater.from(context);
        this.mBinhLuanList = wordList;
        this.context = context;
    }

    @NonNull
    @Override
    public BinhLuanListAdapter.BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.recyclerview_binhluan, parent, false);
        return new BinhLuanViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanListAdapter.BinhLuanViewHolder holder, int position) {
        BinhLuan_Json mCurrent = mBinhLuanList.get(position);
        holder.txtTenNguoiBinhLuan.setText(mCurrent.getTenNguoiBinhLuan());
        holder.txtNoiDungBinhLuan.setText(mCurrent.getNoiDungBinhLuan());
        holder.txtNgayBinhLuan.setText(mCurrent.getNgayBinhLuan());
        holder.txtGioBinhLuan.setText(mCurrent.getGioBinhLuan());
        Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + mCurrent.getAnhNguoiBinhLuan()).into(holder.imgNguoiBinhLuan);
    }

    @Override
    public int getItemCount() {
        return mBinhLuanList.size();
    }

    public class BinhLuanViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTenNguoiBinhLuan;
        public final TextView txtNoiDungBinhLuan;
        public final TextView txtNgayBinhLuan;
        public final ImageView imgNguoiBinhLuan;
        public final TextView txtGioBinhLuan;
        final BinhLuanListAdapter mAdapter;

        public BinhLuanViewHolder(View itemView, BinhLuanListAdapter adapter) {
            super(itemView);
            txtTenNguoiBinhLuan = itemView.findViewById(R.id.txtTenNguoiBinhLuan);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtNgayBinhLuan = itemView.findViewById(R.id.txtNgayBinhLuan);
            imgNguoiBinhLuan = itemView.findViewById(R.id.imgNguoiBinhLuan);
            txtGioBinhLuan = itemView.findViewById(R.id.txtGioBinhLuan);
            this.mAdapter = adapter;
        }
    }
}
