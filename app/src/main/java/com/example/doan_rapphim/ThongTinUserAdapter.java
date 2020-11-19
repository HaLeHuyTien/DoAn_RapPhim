package com.example.doan_rapphim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.packageThongTinUser.ThongTinUser;

import java.util.LinkedList;
import java.util.List;

public class ThongTinUserAdapter extends RecyclerView.Adapter<ThongTinUserAdapter.ThongTinViewHolder>{
    private final LinkedList<ThongTinUserAdapter> mWordList;
    private LayoutInflater mInflater;
    private Context context;

    public ThongTinUserAdapter(Context context, LinkedList<ThongTinUserAdapter> mWordList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mWordList = mWordList;
    }
    @NonNull
    @Override
    public ThongTinUserAdapter.ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.fragment_thong_tin_nguoi_dung,parent,false);
        return new ThongTinUserAdapter.ThongTinViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinUserAdapter.ThongTinViewHolder holder, int position) {
        ThongTinUserAdapter mCurrent = mWordList.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThongTinViewHolder extends RecyclerView.ViewHolder {
        private  TextView txtHVT;
        private  TextView txtEmail;
        private  TextView txtSDT;
        private  TextView txtNgaySinh;
        private  TextView txtDiaChi;
        public ThongTinViewHolder(@NonNull View itemView, ThongTinUserAdapter thongTinUserAdapter) {
            super(itemView);
        }
    }
}
