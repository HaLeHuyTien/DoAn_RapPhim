package com.example.doan_rapphim.packageTrangChiTiet;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;

import java.util.LinkedList;

public class DienVienListAdapter extends RecyclerView.Adapter<DienVienListAdapter.DienVienViewHolder> {
    private final LinkedList<DienVienJson> mDienVienList;
    private LayoutInflater layoutInflater;
    private Context context;

    public DienVienListAdapter(Context context, LinkedList<DienVienJson> wordList){
        layoutInflater = LayoutInflater.from(context);
        this.mDienVienList = wordList;
        this.context = context;
    }

    @NonNull
    @Override
    public DienVienListAdapter.DienVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.recyclerview_dienvien,parent,false);
        return  new DienVienViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull DienVienListAdapter.DienVienViewHolder holder, int position) {
        DienVienJson mCurrent = mDienVienList.get(position);
        holder.txtTenDV.setText(mCurrent.getTenDV());
        int resID = this.context.getResources().getIdentifier(mCurrent.getHinhDV(),"drawable",this.context.getPackageName());
        holder.imgHinhDV.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return mDienVienList.size();
    }

    public class DienVienViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgHinhDV;
        public final TextView txtTenDV;
        final DienVienListAdapter mAdapter;
        public DienVienViewHolder(View itemView,DienVienListAdapter adapter){
            super(itemView);
            imgHinhDV = itemView.findViewById(R.id.HinhDV);
            txtTenDV = itemView.findViewById(R.id.txtTenDV);
            this.mAdapter = adapter;
        }
    }
}