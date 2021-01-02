 package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan_rapphim.MainActivity;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy.DangKyActivity;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TabTaiKhoan_Fragment;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThongTinUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class DangNhapFragment extends Fragment {
private Button btnDN,btnDK;
private EditText edtEmail,edtMatKhau;
private ImageButton imgHidePass;
private String Email,MatKhau;
private TextView txtQuenMatKhau;
private Integer x = 0;
private MainActivity mainActivity;
private String URLDangNhap = "http://0306181355.pixelcent.com/Cinema/KiemTraDangNhap.php";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
        btnDN = view.findViewById(R.id.btnDN);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTraDangNhap kiemTraDangNhap = new KiemTraDangNhap();
                kiemTraDangNhap.execute();
            }
        });
        imgHidePass=view.findViewById(R.id.imgButtonDate);
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

        initPreferences();




        return view;
    }

    protected void replaceFragmentContent(Fragment fragment,int ID, String Hinh) {

        if (fragment != null) {

            FragmentManager fmgr = getActivity().getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.container_body, fragment);
            editor.putInt("DATA1",ID);
            editor.putString("DATA2",Hinh);
            IDUser.idUser = ID;
            IDUser.HinhUser = Hinh;
            editor.commit();

            //IDUser.idUser = ID;
            //IDUser.HinhUser = Hinh;

            ft.commit();

        }

    }

    private class KiemTraDangNhap extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(URLDangNhap);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
            mWordList.clear();
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");
            int a = 0;

                for(int i =0;i<jsonArray.length();i++)
                {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                Integer ID = jsonObject1.getInt("ID");
                String MatKhau = jsonObject1.getString("MatKhau");
                String Email = jsonObject1.getString("Email");
                String Hinh = jsonObject1.getString("Hinh");

                if(edtMatKhau.getText().toString().equals(MatKhau) && edtEmail.getText().toString().equals(Email)) {
                    a = 1;
                    replaceFragmentContent(new TabTaiKhoan_Fragment(),ID,Hinh);
                    break;
                }
                }
                if(a == 0)
                    Toast.makeText(getContext(),"Đăng nhập thất bại !",Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void initPreferences(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
    }


}
