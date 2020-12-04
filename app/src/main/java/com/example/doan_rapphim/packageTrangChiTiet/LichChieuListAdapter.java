package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;

import java.util.LinkedList;

public class LichChieuListAdapter extends RecyclerView.Adapter<LichChieuListAdapter.LichChieuViewHolder> {
    private final LinkedList<LichChieu_Json> mLichChieuList;
    private LayoutInflater mInflater;
    public LichChieuListAdapter(Context context, LinkedList<LichChieu_Json> wordList){
        mInflater = LayoutInflater.from(context);
        this.mLichChieuList = wordList;
    }
    @NonNull
    @Override
    public LichChieuListAdapter.LichChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_lichchieu,parent,false);
        return new LichChieuViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LichChieuListAdapter.LichChieuViewHolder holder, int position) {
        LichChieu_Json mCurrent = mLichChieuList.get(position);
        holder.txtTenRap.setText(mCurrent.getTenRap());
        String a[] = mCurrent.getXuatChieu();
        for(int i =0;i<a.length;i++){
            holder.btnGioChieu[i].setText(a[i]);
        }
    }

    @Override
    public int getItemCount() {
        return mLichChieuList.size();
    }

    public class LichChieuViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTenRap;
        public final Button btnGioChieu[] = new Button[8];
        final LichChieuListAdapter mAdapter;
        public LichChieuViewHolder(View itemView, LichChieuListAdapter adapter){
            super(itemView);
            txtTenRap = itemView.findViewById(R.id.txtTenRap);
            btnGioChieu[0] = itemView.findViewById(R.id.btnGioChieu1);
            btnGioChieu[1] = itemView.findViewById(R.id.btnGioChieu2);
            btnGioChieu[2] = itemView.findViewById(R.id.btnGioChieu3);
            btnGioChieu[3] = itemView.findViewById(R.id.btnGioChieu4);
            btnGioChieu[4] = itemView.findViewById(R.id.btnGioChieu5);
            btnGioChieu[5] = itemView.findViewById(R.id.btnGioChieu6);
            btnGioChieu[6] = itemView.findViewById(R.id.btnGioChieu7);
            btnGioChieu[7] = itemView.findViewById(R.id.btnGioChieu8);
            this.mAdapter = adapter;
        }
    }
}
