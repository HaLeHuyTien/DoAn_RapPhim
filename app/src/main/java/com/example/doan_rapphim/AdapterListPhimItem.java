package com.example.doan_rapphim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class AdapterListPhimItem extends RecyclerView.Adapter<AdapterListPhimItem.WordViewHolder> {
    private final LinkedList<Phim> mWordList;
    private LayoutInflater mInflater;
    private Context context;

    public AdapterListPhimItem(Context context,LinkedList<Phim> wordList){
        mInflater=LayoutInflater.from(context);
        this.mWordList=wordList;
        this.context = context;}
    @NonNull
    @Override
    public AdapterListPhimItem.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.listphim_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListPhimItem.WordViewHolder holder, int position) {
        Phim mCurrent=mWordList.get(position);
        holder.txtTenPhim.setText(mCurrent.getTenPhim());
        holder.txtTheLoai.setText(mCurrent.getTheLoai());
       // holder.wordTextView3.setText(mCurrent.getNoiDung());
        int resID = this.context.getResources().getIdentifier(mCurrent.getHinhAnh(),"drawable",this.context.getPackageName());
        holder.img2.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTenPhim;
        public final TextView txtTheLoai;
        //public final TextView txtSoTuoi;
        //public final TextView txtSoDiem;
        //public final TextView txtHinhAnh;
        //public final TextView txtTrangThai;

        public final ImageView img2;
        final AdapterListPhimItem mAdapter;
        public WordViewHolder(View itemView,AdapterListPhimItem adapterWordList){
            super(itemView);
           txtTenPhim=itemView.findViewById(R.id.edttenphim);
            txtTheLoai=itemView.findViewById(R.id.edttheloai);

            img2=itemView.findViewById(R.id.imgPoster);

            this.mAdapter=adapterWordList;
        }
    }
}
