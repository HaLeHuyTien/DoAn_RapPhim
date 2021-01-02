package com.example.doan_rapphim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.packageTrangChiTiet.IDPhim;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinJson;
import com.example.doan_rapphim.packageTrangChiTiet.TrangChiTiet;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThongTinSoDoGhe;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class AdapterListPhimItem extends RecyclerView.Adapter<AdapterListPhimItem.WordViewHolder> {
    private final LinkedList<ThongTinJson> mWordList;
    private LayoutInflater mInflater;
    private Context context;
    private Activity activity;



    public AdapterListPhimItem(Context context,Activity activity,LinkedList<ThongTinJson> wordList){

        this.mWordList=wordList;
        this.context = context;
        this.activity = activity;
        mInflater=LayoutInflater.from(this.context);
    }
    @NonNull
    @Override
    public AdapterListPhimItem.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.listphim_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterListPhimItem.WordViewHolder holder, int position) {
        ThongTinJson mCurrent=mWordList.get(position);
        holder.txtTenPhim.setText(mCurrent.getTenPhim());
        holder.txtTheLoai.setText(mCurrent.getTheLoai());
        holder.txtDaoDien.setText(mCurrent.getDaoDien());
        holder.txtTuoi.setText(mCurrent.getDoTuoi());

       // holder.wordTextView3.setText(mCurrent.getNoiDung());
        Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + mCurrent.getHinhPhim()).into(holder.img2);

       // int resID = this.context.getResources().getIdentifier(mCurrent.getHinhPhim(),"drawable",this.context.getPackageName());
       // holder.img2.setImageResource(resID);
        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IDPhim.ID = mCurrent.getIDPhim();
                ThongTinSoDoGhe.tenPhim = mCurrent.getTenPhim();
                ThongTinSoDoGhe.tenHinh = mCurrent.getHinhPhim();
                Intent intent=new Intent(activity ,TrangChiTiet.class);
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTenPhim;
        public final TextView txtTheLoai;
        public final TextView txtDaoDien;
        public final TextView txtTuoi;
        public final Button btnChiTiet;
        //public final TextView txtSoTuoi;
        //public final TextView txtSoDiem;
        //public final TextView txtHinhAnh;
        //public final TextView txtTrangThai;

        public final ImageView img2;
        final AdapterListPhimItem mAdapter;
        public WordViewHolder(View itemView,AdapterListPhimItem adapterWordList){
            super(itemView);
           txtTenPhim=itemView.findViewById(R.id.txtRVTenPhim);
           txtTheLoai=itemView.findViewById(R.id.txtTenTL);
           txtTuoi=itemView.findViewById(R.id.txtSoTuoi);
           txtDaoDien= itemView.findViewById(R.id.txtTenDD);
           btnChiTiet = itemView.findViewById(R.id.btnRVChiTiet);

            img2=itemView.findViewById(R.id.imgRVHinhPhim);

            this.mAdapter=adapterWordList;
        }
    }
}
