package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_rapphim.MainActivity;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.ViewPagerAdapter_TrangChu;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy.DangKyActivity;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ReadThongTinUserJson;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TabTaiKhoan;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThongTinUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;

public class DangNhapFragment extends Fragment {
private Button btnDN,btnDK;
private EditText edtEmail,edtMatKhau;
private ImageButton imgHidePass;
private String Email,MatKhau;
private TextView txtQuenMatKhau;
private Integer x = 0;
private BottomNavigationView navigationView;
private MainActivity mainActivity;

    private final LinkedList<ThongTinUser> mWordList = new LinkedList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap,container,false);
        btnDK=view.findViewById(R.id.btnDK);
        edtEmail=view.findViewById(R.id.txtEmailDN);
        edtMatKhau=view.findViewById(R.id.txtPassDN);
        txtQuenMatKhau=view.findViewById(R.id.txtQuenMK);
        mainActivity = (MainActivity)getActivity();
        navigationView = view.findViewById(R.id.bottom_nav);
        txtQuenMatKhau.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity() , QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity() , DangKyActivity.class);
                startActivity(intent);
            }
        });

        btnDN=view.findViewById(R.id.btnDN);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0;
                try {
                    int soluongUser = ReadThongTinUserJson.SoLuongTaiKhoan(getActivity());
                    for(int i = 0; i < soluongUser; i ++) {
                        ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(getActivity(), i);
                        Email = thongTinUser.getEmail();
                        MatKhau = thongTinUser.getMatKhau();
                        if (edtEmail.getText().toString().equals(Email) && edtMatKhau.getText().toString().equals(MatKhau)) {
                            IDUser.idUser = i+1;

                           Intent intent=new Intent(getActivity() , TabTaiKhoan.class);
                            startActivity(intent);
                            a = 1;
                            mainActivity.setup_view_pager();
                            mainActivity.viewPager.setCurrentItem(0);
                            mainActivity.navigationView.getMenu().findItem(R.id.button_home).setChecked(true);
                            break;
                        }
                    }
                    if(a == 0)
                    Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        imgHidePass=view.findViewById(R.id.imgHidePassDN);
        imgHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x == 0)
                {
                    edtMatKhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    x = 1;
                }
                else
                {
                    edtMatKhau.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    x = 0;
                }
            }
        });

        return view;
    }

}
