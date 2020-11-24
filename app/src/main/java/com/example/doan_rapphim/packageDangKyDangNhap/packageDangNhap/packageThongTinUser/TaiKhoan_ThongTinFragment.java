package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.example.doan_rapphim.packageTrangChiTiet.LichChieuFragment;
import com.example.doan_rapphim.packageTrangChiTiet.LichChieuListAdapter;
import com.example.doan_rapphim.packageTrangChiTiet.LichChieu_Json;
import com.example.doan_rapphim.packageTrangChiTiet.ThongTinFragment;

import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TaiKhoan_ThongTinFragment extends Fragment {
    private Button ThayDoiThongTin;
    private Button ThayDoiMatKhau;


    private ImageView txtanhDaiDien;
    private TextView txtHoVaTen;
    private TextView txtEmail;
    private TextView txtSDT;
    private TextView txtNgaySinh;
    private TextView txtDiaChi;
    private EditText txtMKC;

    private final LinkedList<ThongTinUser> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ThongTinUserAdapter mAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ThongTinUser getInstance() {
        ThongTinUser thongTinUser = new ThongTinUser();
        return thongTinUser;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongTinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongTinFragment newInstance(String param1, String param2) {
        ThongTinFragment fragment = new ThongTinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        //mRecyclerView = view.findViewById(R.id.recycler_view_thongtin);
        txtanhDaiDien = view.findViewById(R.id.imgHinhDaiDienTT1);
        txtHoVaTen = view.findViewById(R.id.txtHoVaTenTT);
        txtEmail = view.findViewById(R.id.txtEmailTT);
        txtSDT = view.findViewById(R.id.txtSDTTT);
        txtNgaySinh = view.findViewById(R.id.txtNgaySinhTT);
        txtDiaChi = view.findViewById(R.id.txtDiaChiTT);
        txtMKC = view.findViewById(R.id.editTextMKCuTT);

        HienThiUser();
        // Inflate the layout for this fragment
        ThayDoiThongTin = view.findViewById(R.id.btnThayDoiTT);
        ThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiThongTin.class);
                startActivity(intent);
            }
        });
        ThayDoiMatKhau = view.findViewById(R.id.btnThayDoiMK);
        ThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiMatKhau.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void HienThiUser() {
        try {

            //for(int i = 0; i < soluong ; i ++) {
                ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(getActivity(), IDUser.idUser);
                //Truyen Id if(thongTinUser.getID() == )
                txtEmail.setText(thongTinUser.getEmail());
                int resID = this.getContext().getResources().getIdentifier(thongTinUser.getAnh(),"drawable",this.getContext().getPackageName());
                txtanhDaiDien.setImageResource(resID);
                txtHoVaTen.setText(thongTinUser.getHoVaTen());
                txtSDT.setText(thongTinUser.getSDT());
                txtNgaySinh.setText(thongTinUser.getNgaySinh());
                txtDiaChi.setText(thongTinUser.getDiaChi());
            //}
        }catch (Exception e){
            txtEmail.setText("Error");
        }

    }
}