package com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.PhuongXa;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.QuanHuyen;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ReadThongTinUserJson;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiThongTin;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThongTinUser;
import com.example.doan_rapphim.packageTrangChiTiet.IDPhim;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThongTinSoDoGhe;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;
    private ArrayAdapter<QuanHuyen> spinnerListArrayAdapterQuanHuyen;
    private ArrayAdapter<PhuongXa> spinnerListArrayAdapterPhuongXa;
    private String[] categories = {"Hà Nội", "TP.HCM" , "Đà Nẵng"};
    public int IDThanhPhoTinh;
    public int IDThanhPhoQuanHuyen;
    public int IDThanhPhoPhuongXa;


    private Button btnDangKy;
    private EditText edtHoTenDK;
    private EditText edtEmailDK;
    private EditText edtSDTDangKy;

    private EditText edtMatKhauDK;
    private EditText edtNhapLaiMatKhauDK;
    private EditText edtNgaySinh;
    private ImageButton imgLichNgaySinhFormDangKy;
    private String EmailDK;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private Button btnTaiHinhDK;
    private ImageView imgDK;
    private String HinhBase64;
    public final static int PICK_IMAGE_REQUEST = 1;
    //API
    private  String InsertKhachHang ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        spnTinhTp = findViewById(R.id.spinnerThanhPhoDK);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyenDK);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuongDK);

        btnDangKy = findViewById(R.id.btnDangKyFormDK);
        edtHoTenDK = findViewById(R.id.txtHoTenDK);
        edtNgaySinh=findViewById(R.id.editTextDateTT);
        edtEmailDK=findViewById(R.id.txtEmailDK);
        edtSDTDangKy=findViewById(R.id.editTextSDTTT);
        edtMatKhauDK=findViewById(R.id.txtMatKhauDK);
        edtNhapLaiMatKhauDK=findViewById(R.id.txtNhapLaiMatKhauDK);
        btnTaiHinhDK=findViewById(R.id.btnTaiHinh);
        imgDK=findViewById(R.id.imgDK);
        btnTaiHinhDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
        imgLichNgaySinhFormDangKy=findViewById(R.id.imgButtonDate);
        imgLichNgaySinhFormDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgaySinh();
            }
        });
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        edtNgaySinh.setText(date);

        SpinnerView();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DangKy(v);


            }
        });
    }

    public void ChonNgaySinh(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                if(dayOfMonth > 9)
                    edtNgaySinh.setText(year +"-" + thang + "-" + dayOfMonth);
                else
                    edtNgaySinh.setText(year +"-" + thang + "-" +"0"+ dayOfMonth);
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;


            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

    public void SpinnerView() {

        //Load Dữ liệu và spinner TpTinh
        spnTinhTp.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories));
        spnTinhTp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0 ) {
                    IDThanhPhoTinh = position;
                    getSelectedQuanHuyen(position);
                }
                else {
                    Toast.makeText(DangKyActivity.this,"Selected",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //Load Dữ liệu và spinner QuanHuyen
        spnHuyenQuan.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getQuanHuyenList()));
        spnHuyenQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0 ) {
                    getSelectedXaPhuong(position);
                }
                else {
                    Toast.makeText(DangKyActivity.this,"Selected",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Loat dữ liệu XaPhuong
        spnXaPhuong.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getXaPhuongList()));

    }

    //Dữ liệu của QuanHuyen
    public ArrayList<QuanHuyen> getQuanHuyenList() {
        ArrayList<QuanHuyen> data = new ArrayList<>();
        data.clear();

        //Quận của TP.HCM
        data.add(new QuanHuyen("1",1));
        data.add(new QuanHuyen("2", 1));
        data.add(new QuanHuyen("3", 1));
        data.add(new QuanHuyen("4", 1));
        data.add(new QuanHuyen("5", 1));
        data.add(new QuanHuyen("6", 1));
        data.add(new QuanHuyen("7", 1));
        data.add(new QuanHuyen("8", 1));
        data.add(new QuanHuyen("9", 1));
        data.add(new QuanHuyen("10", 1));
        data.add(new QuanHuyen("11", 1));
        data.add(new QuanHuyen("12", 1));
        data.add(new QuanHuyen("Bình Tân", 1));
        data.add(new QuanHuyen("Bình Thạnh", 1));
        data.add(new QuanHuyen("Gò Vấp", 1));
        data.add(new QuanHuyen("Phú Nhuận", 1));
        data.add(new QuanHuyen("Tân Bình", 1));
        data.add(new QuanHuyen("Tân Phú", 1));
        data.add(new QuanHuyen("Thủ Đức", 1));
        data.add(new QuanHuyen("Bình Chánh", 1));
        data.add(new QuanHuyen("Cần Giờ", 1));
        data.add(new QuanHuyen("Củ Chi", 1));
        data.add(new QuanHuyen("Hóc Môn", 1));
        data.add(new QuanHuyen("Nhà Bè", 1));

        //Quận của Hà Nội
        data.add(new QuanHuyen("Bắc Từ Liêm",0));
        data.add(new QuanHuyen("Ba Đình",0));
        data.add(new QuanHuyen("Cầu Giấy",0));
        data.add(new QuanHuyen("Đống Đa",0));
        data.add(new QuanHuyen("Hai Bà Trưng",0));
        data.add(new QuanHuyen("Hoàn Kiếm",0));
        data.add(new QuanHuyen("Hà Đông",0));
        data.add(new QuanHuyen("Hoàng Mai",0));
        data.add(new QuanHuyen("Long Biên",0));
        data.add(new QuanHuyen("Thanh Xuân",0));
        data.add(new QuanHuyen("Tây Hồ",0));
        data.add(new QuanHuyen("Nam Từ Liêm",0));

        //Các Huyện của Đà Nẵng
        data.add(new QuanHuyen("Ngũ Hành Sơn",2));
        data.add(new QuanHuyen("Hòa Vang",2));
        data.add(new QuanHuyen("Hoàng Sa",2));
        data.add(new QuanHuyen("Thanh Khê",2));
        data.add(new QuanHuyen("Sơn Trà",2));
        data.add(new QuanHuyen("Liên Chiểu",2));
        data.add(new QuanHuyen("Hải Châu",2));
        data.add(new QuanHuyen("Cẩm Lệ",2));



        return data;


    }

    //Dữ liệu của Xã phường
    public ArrayList<PhuongXa> getXaPhuongList() {
        ArrayList<PhuongXa> data = new ArrayList<>();
        data.clear();

        //Phường Xã của Quận 1
        data.add(new PhuongXa("Bến Nghé",1,0));
        data.add(new PhuongXa("Bến Thành",1,0));
        data.add(new PhuongXa("Cô Giang",1,0));
        data.add(new PhuongXa("Cầu Kho",1,0));
        data.add(new PhuongXa("Cầu Ông Lãnh",1,0));
        data.add(new PhuongXa("Đa Kao",1,0));
        data.add(new PhuongXa("Nguyễn Cư Trinh",1,0));
        data.add(new PhuongXa("Nguyễn Thái Bình",1,0));
        data.add(new PhuongXa("Phạm Ngũ Lão",1,0));
        data.add(new PhuongXa("Tân Định",1,0));
        data.add(new PhuongXa("An Khánh",1,0));

        //Phường Xã của Quận 2
        data.add(new PhuongXa("An Khánh",1,1));
        data.add(new PhuongXa("An Lợi Đông",1,1));
        data.add(new PhuongXa("An Phú",1,1));
        data.add(new PhuongXa("Bình An",1,1));
        data.add(new PhuongXa("Bình Khánh",1,1));
        data.add(new PhuongXa("Bình Trưng Đông",1,1));
        data.add(new PhuongXa("Bình Trưng Tây",1,1));
        data.add(new PhuongXa("Cát Lái",1,1));
        data.add(new PhuongXa("Thạch Mỹ Lợi",1,1));
        data.add(new PhuongXa("Thảo Điền",1,1));
        data.add(new PhuongXa("Thủ Khiêm",1,1));

        //Phường Xã của Quận Bắc Từ Liêm
        data.add(new PhuongXa("Cổ Nhuế 1",0,0));
        data.add(new PhuongXa("Cổ Nhuế 2",0,0));
        data.add(new PhuongXa("Đức Thắng",0,0));
        data.add(new PhuongXa("Đông Ngạc",0,0));
        data.add(new PhuongXa("Thụy Phương",0,0));
        data.add(new PhuongXa("Liên Mạc",0,0));
        data.add(new PhuongXa("Thượng Cát",0,0));
        data.add(new PhuongXa("Tây Tựu",0,0));
        data.add(new PhuongXa("Minh Khai",0,0));
        data.add(new PhuongXa("Phú Diễn",0,0));
        data.add(new PhuongXa("Xuân Đỉnh",0,0));
        data.add(new PhuongXa("Xuân Tảo",0,0));

        // Phường Xã của Quận Ba Đình
        data.add(new PhuongXa("Cống Vị",0,1));
        data.add(new PhuongXa("Điện Biên",0,1));
        data.add(new PhuongXa("Đội Cấn",0,1));
        data.add(new PhuongXa("Giảng Võ",0,1));
        data.add(new PhuongXa("Kim Mã",0,1));
        data.add(new PhuongXa("Liễu Giai",0,1));
        data.add(new PhuongXa("Ngọc Hà",0,1));
        data.add(new PhuongXa("Ngọc Khánh",0,1));
        data.add(new PhuongXa("Nguyễn Trung Trực",0,1));
        data.add(new PhuongXa("Phúc Xá",0,1));
        data.add(new PhuongXa("Quán Thánh",0,1));
        data.add(new PhuongXa("Thành Công",0,1));
        data.add(new PhuongXa("Trúc Bạch",0,1));
        data.add(new PhuongXa("Vĩnh Phúc",0,1));

        //Phường Xã của Quận Ngũ Hành Sơn
        data.add(new PhuongXa("Hòa Hải",2,0));
        data.add(new PhuongXa("Hòa Quý",2,0));
        data.add(new PhuongXa("Hòa Khuê Mỹ",2,0));
        data.add(new PhuongXa("Mỹ An",2,0));

        //Phường Xã của Quận Hòa Vang
        data.add(new PhuongXa("Hòa Châu",2,1));
        data.add(new PhuongXa("Hòa Tiến",2,1));
        data.add(new PhuongXa("Hòa Phước",2,1));
        data.add(new PhuongXa("Hòa Phong",2,1));

        return data;
    }

    //Add DL vào spinner QuanHuyen
    public void getSelectedQuanHuyen(int IDThanhPho) {
        ArrayList<QuanHuyen> quanHuyens = new ArrayList<>();
        for (QuanHuyen quanHuyen : getQuanHuyenList()) {
            if(quanHuyen.getIDThanhPhoTinh() == IDThanhPho) {
                quanHuyens.add(quanHuyen);

            }
        }

        spinnerListArrayAdapterQuanHuyen = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,quanHuyens);

        spnHuyenQuan.setAdapter(spinnerListArrayAdapterQuanHuyen);
    }


    //Add DL vào spinner XaPhuong,,,
    public void getSelectedXaPhuong(int IDQuanHuyen) {
        ArrayList<PhuongXa> phuongXas = new ArrayList<>();


        for (PhuongXa phuongXa : getXaPhuongList()) {
            if(phuongXa.getIDQuanHuyen()== IDQuanHuyen && phuongXa.getIDThanhPhoTinh() == IDThanhPhoTinh) {
                phuongXas.add(phuongXa);
            }
        }
        spinnerListArrayAdapterPhuongXa = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,phuongXas);

        spnXaPhuong.setAdapter(spinnerListArrayAdapterPhuongXa);
    }


    public void DangKy(View view){
        String HoTen=edtHoTenDK.getText().toString();
        String Email=edtEmailDK.getText().toString();
        String SDT=edtSDTDangKy.getText().toString();
        String DiaChi= spnXaPhuong.getSelectedItem().toString()+","+spnHuyenQuan.getSelectedItem().toString()+","+spnTinhTp.getSelectedItem().toString();
        String MatKhau=edtMatKhauDK.getText().toString();
        String NhapLaiMatKhau=edtNhapLaiMatKhauDK.getText().toString();
        if(HoTen.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Họ và Tên cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(kiemTraNhapDung3Tu(HoTen)==true){
            Toast.makeText(this,"Bạn chưa nhập đúng 3 từ Họ và Tên trong form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(Email.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Email cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(SDT.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Số điện thoại cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(DiaChi.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Địa chỉ cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(MatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Mật khẩu cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(NhapLaiMatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa Nhập lại mật khẩu cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else {
            int a = 0;
            try {
                int soluongUser = ReadThongTinUserJson.SoLuongTaiKhoan(this);
                for(int i = 0; i < soluongUser; i ++) {
                    ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(this, i);
                    Email = thongTinUser.getEmail();
                    if (edtEmailDK.getText().toString().equals(Email) ) {
                        IDUser.idUser = i;
                        Toast.makeText(this, "Email bạn nhập đã tồn tại, hãy nhập Email khác!", Toast.LENGTH_SHORT).show();
                        a = 1;
                        break;
                    }
                }
                if(a == 0) {
                    Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
                    INSERT_KHACHHANG insert_khachhang=new INSERT_KHACHHANG();
                    insert_khachhang.execute();
                    UploadHinh(view);

                    finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    public static boolean kiemTraNhapDung3Tu(final String kt){
        int tam=1;
        if(kt!=null){
            for(int i=0;i<kt.length();i++){
                if(Character.isWhitespace(kt.charAt(i))){
                    tam++;
                }
            }
        }
        if(tam>2)
            return false;
        else return true;
    }
    //Click đổi ảnh
    private void pickImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a file to Upload"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            imgDK.setImageURI(uri);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imgDK.setDrawingCacheEnabled(true);
            Bitmap bitmap = imgDK.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            HinhBase64 = imageString;
        }
    }
    private class INSERT_KHACHHANG extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    InsertKhachHang = "http://0306181355.pixelcent.com/rapphim/public/api/themkhachhang/"+edtHoTenDK.getText().toString()+"/"+edtEmailDK.getText().toString()+"/"+edtSDTDangKy.getText().toString()+"/"+edtNgaySinh.getText().toString()+"/"+spnXaPhuong.getSelectedItem().toString()+","+spnHuyenQuan.getSelectedItem().toString()+","+spnTinhTp.getSelectedItem().toString()+"/"+edtMatKhauDK.getText().toString()+"/"+edtSDTDangKy.getText().toString()+".png";
                     url = new URL(InsertKhachHang);
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

        }
    }

    public void UploadHinh(View view){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://0306181355.pixelcent.com/rapphim/public/api/addhinh",
                response -> {},error -> {}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hinh",HinhBase64);
                params.put("TenHinh",edtSDTDangKy.getText().toString());
                return params;
            }
        };


        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


}

