package com.example.doan_rapphim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Photo_Slide_Adapter extends PagerAdapter {

    private Context context;
    private List<Photo> listPhoto;

    public Photo_Slide_Adapter(Context context, List<Photo> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgPhoto = view.findViewById(R.id.slide_img_1);
        TextView txtTenLoai = view.findViewById(R.id.theloaislide);
        TextView txtTuoi = view.findViewById(R.id.Tuoislide);
        TextView txtDanhGia = view.findViewById(R.id.danhgiaslide);
        Photo photo = listPhoto.get(position);
        if(photo != null){
            Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + photo.getTenHinh()).into(imgPhoto);
            txtTenLoai.setText(photo.getTenLoai());
            txtDanhGia.setText(photo.getDiem().toString());
            txtTuoi.setText(photo.getTuoi());
        }
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(listPhoto != null)
            return listPhoto.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}