package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;

import java.util.LinkedList;

public class ThongTinUserAdapter extends RecyclerView.Adapter<ThongTinUserAdapter.ThongTinViewHolder>{
    private final LinkedList<ThongTinUser> mWordList;
    private LayoutInflater mInflater;
    private Context context;

    public ThongTinUserAdapter(Context context, LinkedList<ThongTinUser> mWordList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mWordList = mWordList;
    }
    @NonNull
    @Override
    public ThongTinUserAdapter.ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.fragment_thong_tin_nguoi_dung,parent,false);
        return new ThongTinViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinUserAdapter.ThongTinViewHolder holder, int position) {
        ThongTinUser mCurrent = mWordList.get(position);
        holder.txtHVT.setText(mCurrent.getHoVaTen());
        holder.txtEmail.setText(mCurrent.getEmail());
        holder.txtSDT.setText(mCurrent.getSDT());
        holder.txtNgaySinh.setText(mCurrent.getNgaySinh());
        holder.txtDiaChi.setText(mCurrent.getDiaChi());
        holder.txtMKC.setText(mCurrent.getMatKhau());
        int resID = this.context.getResources().getIdentifier(mCurrent.getAnh(),"drawable",this.context.getPackageName());
        holder.imgAnhDaiDien.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class ThongTinViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgAnhDaiDien;
        public final  TextView txtHVT;
        public final  TextView txtEmail;
        public final  TextView txtSDT;
        public final  TextView txtNgaySinh;
        public final  TextView txtDiaChi;
        public final EditText txtMKC;
        final ThongTinUserAdapter mAdapter;
        public ThongTinViewHolder(@NonNull View itemView, ThongTinUserAdapter thongTinUserAdapter) {
            super(itemView);
            txtHVT = itemView.findViewById(R.id.txtHoVaTenTT);
            txtEmail = itemView.findViewById(R.id.txtEmailTT);
            txtSDT = itemView.findViewById(R.id.txtSDTTT);
            txtNgaySinh = itemView.findViewById(R.id.txtNgaySinhTT);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChiTT);
            txtMKC = itemView.findViewById(R.id.editTextMKCuTT);
            imgAnhDaiDien = itemView.findViewById(R.id.imgHinhDaiDienTT1);
            this.mAdapter = thongTinUserAdapter;
        }
    }
}
