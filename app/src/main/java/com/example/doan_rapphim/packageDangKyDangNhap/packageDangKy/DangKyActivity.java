package com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.TaiKhoan_ThongTinFragment;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThayDoiThongTin;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser.ThongTinUser;
import com.example.doan_rapphim.packageTrangChiTiet.IDPhim;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThongTinSoDoGhe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;
    private ArrayAdapter<QuanHuyen> spinnerListArrayAdapterQuanHuyen;
    private ArrayAdapter<PhuongXa> spinnerListArrayAdapterPhuongXa;
    private String[] categories = {"TP Hà Nội", "TP Hồ Chí Minh" , "TP Đà Nẵng"};
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

    private Integer EmailKT=0;
    private Integer SDTKT=0;

    private String jsonURL="http://0306181355.pixelcent.com/Cinema/KiemTraEmailSDT.php?Email=";
    private String URLKTDK;
    public final static int PICK_IMAGE_REQUEST = 1;
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

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
        imgDK.setImageResource(R.drawable.admin);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.admin);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        HinhBase64 = imageString;


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
        data.add(new QuanHuyen("Quận 1",1));
        data.add(new QuanHuyen("Quận 2", 1));
        data.add(new QuanHuyen("Quận 3", 1));
        data.add(new QuanHuyen("Quận 4", 1));
        data.add(new QuanHuyen("Quận 5", 1));
        data.add(new QuanHuyen("Quận 6", 1));
        data.add(new QuanHuyen("Quận 7", 1));
        data.add(new QuanHuyen("Quận 8", 1));
        data.add(new QuanHuyen("Quận 9", 1));
        data.add(new QuanHuyen("Quận 10", 1));
        data.add(new QuanHuyen("Quận 11", 1));
        data.add(new QuanHuyen("Quận 12", 1));
        data.add(new QuanHuyen("Quận Bình Tân", 1));
        data.add(new QuanHuyen("Quận Bình Thạnh", 1));
        data.add(new QuanHuyen("Quận Gò Vấp", 1));
        data.add(new QuanHuyen("Quận Phú Nhuận", 1));
        data.add(new QuanHuyen("Quận ân Bình", 1));
        data.add(new QuanHuyen("Quận Tân Phú", 1));
        data.add(new QuanHuyen("Quận Thủ Đức", 1));
        data.add(new QuanHuyen("Quận Bình Chánh", 1));
        data.add(new QuanHuyen("Quận Cần Giờ", 1));
        data.add(new QuanHuyen("Quận Củ Chi", 1));
        data.add(new QuanHuyen("Quận Hóc Môn", 1));
        data.add(new QuanHuyen("Quận Nhà Bè", 1));

        //Quận của Hà Nội
        data.add(new QuanHuyen("Quận Bắc Từ Liêm",0));
        data.add(new QuanHuyen("Quận Ba Đình",0));
        data.add(new QuanHuyen("Quận Cầu Giấy",0));
        data.add(new QuanHuyen("Quận Đống Đa",0));
        data.add(new QuanHuyen("Quận Hai Bà Trưng",0));
        data.add(new QuanHuyen("Quận Hoàn Kiếm",0));
        data.add(new QuanHuyen("Quận Hà Đông",0));
        data.add(new QuanHuyen("Quận Hoàng Mai",0));
        data.add(new QuanHuyen("Quận Long Biên",0));
        data.add(new QuanHuyen("Quận Thanh Xuân",0));
        data.add(new QuanHuyen("Quận Tây Hồ",0));
        data.add(new QuanHuyen("Quận Nam Từ Liêm",0));

        //Các Huyện của Đà Nẵng
        data.add(new QuanHuyen("Quận Ngũ Hành Sơn",2));
        data.add(new QuanHuyen("Huyện Hòa Vang",2));
        data.add(new QuanHuyen("Huyện Hoàng Sa",2));
        data.add(new QuanHuyen("Quận Thanh Khê",2));
        data.add(new QuanHuyen("Quận Sơn Trà",2));
        data.add(new QuanHuyen("Quận Liên Chiểu",2));
        data.add(new QuanHuyen("Quận Hải Châu",2));
        data.add(new QuanHuyen("Quận Cẩm Lệ",2));

        return data;


    }

    //Dữ liệu của Xã phường
    public ArrayList<PhuongXa> getXaPhuongList() {
        ArrayList<PhuongXa> data = new ArrayList<>();
        data.clear();

        //Phường Xã của Quận 1
        data.add(new PhuongXa("Phường Bến Nghé",1,0));
        data.add(new PhuongXa("Phường Bến Thành",1,0));
        data.add(new PhuongXa("Phường Cô Giang",1,0));
        data.add(new PhuongXa("Phường Cầu Kho",1,0));
        data.add(new PhuongXa("Phường Cầu Ông Lãnh",1,0));
        data.add(new PhuongXa("Phường Đa Kao",1,0));
        data.add(new PhuongXa("Phường Nguyễn Cư Trinh",1,0));
        data.add(new PhuongXa("Phường Nguyễn Thái Bình",1,0));
        data.add(new PhuongXa("Phường Phạm Ngũ Lão",1,0));
        data.add(new PhuongXa("Phường Tân Định",1,0));
        data.add(new PhuongXa("Phường An Khánh",1,0));

        //Phường Xã của Quận 2
        data.add(new PhuongXa("Phường An Khánh",1,1));
        data.add(new PhuongXa("Phường An Lợi Đông",1,1));
        data.add(new PhuongXa("Phường An Phú",1,1));
        data.add(new PhuongXa("Phường Bình An",1,1));
        data.add(new PhuongXa("Phường Bình Khánh",1,1));
        data.add(new PhuongXa("Phường Bình Trưng Đông",1,1));
        data.add(new PhuongXa("Phường Bình Trưng Tây",1,1));
        data.add(new PhuongXa("Phường Cát Lái",1,1));
        data.add(new PhuongXa("Phường Thạch Mỹ Lợi",1,1));
        data.add(new PhuongXa("Phường Thảo Điền",1,1));
        data.add(new PhuongXa("Phường Thủ Khiêm",1,1));

        //Phường Xã của Quận Bắc Từ Liêm
        data.add(new PhuongXa("Phường Cổ Nhuế 1",0,0));
        data.add(new PhuongXa("Phường Cổ Nhuế 2",0,0));
        data.add(new PhuongXa("Phường Đức Thắng",0,0));
        data.add(new PhuongXa("Phường Đông Ngạc",0,0));
        data.add(new PhuongXa("Phường Thụy Phương",0,0));
        data.add(new PhuongXa("Phường Liên Mạc",0,0));
        data.add(new PhuongXa("Phường Thượng Cát",0,0));
        data.add(new PhuongXa("Phường Tây Tựu",0,0));
        data.add(new PhuongXa("Phường Minh Khai",0,0));
        data.add(new PhuongXa("Phường Phú Diễn",0,0));
        data.add(new PhuongXa("Phường Xuân Đỉnh",0,0));
        data.add(new PhuongXa("Phường Xuân Tảo",0,0));

        // Phường Xã của Quận Ba Đình
        data.add(new PhuongXa("Phường Cống Vị",0,1));
        data.add(new PhuongXa("Phường Điện Biên",0,1));
        data.add(new PhuongXa("Phường Đội Cấn",0,1));
        data.add(new PhuongXa("Phường Giảng Võ",0,1));
        data.add(new PhuongXa("Phường Kim Mã",0,1));
        data.add(new PhuongXa("Phường Liễu Giai",0,1));
        data.add(new PhuongXa("Phường Ngọc Hà",0,1));
        data.add(new PhuongXa("Phường Ngọc Khánh",0,1));
        data.add(new PhuongXa("Phường Nguyễn Trung Trực",0,1));
        data.add(new PhuongXa("Phường Phúc Xá",0,1));
        data.add(new PhuongXa("Phường Quán Thánh",0,1));
        data.add(new PhuongXa("Phường Thành Công",0,1));
        data.add(new PhuongXa("Phường Trúc Bạch",0,1));
        data.add(new PhuongXa("Phường Vĩnh Phúc",0,1));

        //Phường Xã của Quận Ngũ Hành Sơn
        data.add(new PhuongXa("Phường Hòa Hải",2,0));
        data.add(new PhuongXa("Phường Hòa Quý",2,0));
        data.add(new PhuongXa("Phường Hòa Khuê Mỹ",2,0));
        data.add(new PhuongXa("Phường Mỹ An",2,0));

        //Phường Xã của Quận Hòa Vang
        data.add(new PhuongXa("Phường Hòa Châu",2,1));
        data.add(new PhuongXa("Phường Hòa Tiến",2,1));
        data.add(new PhuongXa("Phường Hòa Phước",2,1));
        data.add(new PhuongXa("Phường Hòa Phong",2,1));

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
    private boolean CheckEmail(String sEmailId) {

        return EMAIL_PATTERN.matcher(sEmailId).matches();
    }


    public void DangKy(View view){
        String HoTen=edtHoTenDK.getText().toString();
        String Email=edtEmailDK.getText().toString();
        String SDT=edtSDTDangKy.getText().toString();
        String DiaChi=spnXaPhuong.getSelectedItem().toString() + ", " +spnHuyenQuan.getSelectedItem().toString() + ", " + spnTinhTp.getSelectedItem().toString();
        String MatKhau=edtMatKhauDK.getText().toString();
        String NhapLaiMatKhau=edtNhapLaiMatKhauDK.getText().toString();



        if(HoTen.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Họ và Tên cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(HoTen.length()<3 || HoTen.length()>20){
            Toast.makeText(this,"Họ và tên phải lớn hơn 3 ký tự và bé hơn 20 ký tự !",Toast.LENGTH_SHORT).show();
        }
        else if(Email.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Email cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(!CheckEmail(Email)){
            Toast.makeText(this, "Bạn nhập Email chưa đúng định dạng!", Toast.LENGTH_SHORT).show();
        }else if(EmailKT==1){
            Toast.makeText(this, "Email đã sử dụng, bạn hãy dùng Email khác !", Toast.LENGTH_SHORT).show();
        }
        else if(SDT.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Số điện thoại cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(SDT.length()<10){
            Toast.makeText(this,"Số điện thoại phải 10 ký tự!",Toast.LENGTH_SHORT).show();
        }else if(SDTKT==1){
            Toast.makeText(this,"Số điện thoại đã sử dụng, bạn hãy dùng số điện thoại khác !",Toast.LENGTH_SHORT).show();
        }
        else if(MatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Mật khẩu cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(NhapLaiMatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa Nhập lại Mật khẩu cho form Đăng ký!",Toast.LENGTH_SHORT).show();
        }
        else if(NhapLaiMatKhau.equals(MatKhau)){
                    KiemTraEmail kiemTraEmail = new KiemTraEmail(view,this);
                    kiemTraEmail.execute();
        }
        else {
            Toast.makeText(this,"Nhập lại mật khẩu phải trùng với Mật khẩu vừa nhập!",Toast.LENGTH_SHORT).show();
        }


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

    public class KiemTraEmail extends AsyncTask<String, String, String> {

        private View view;
        private Activity activity;

        public KiemTraEmail(View view, Activity activity){
            this.view = view;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    URLKTDK = jsonURL +edtEmailDK.getText().toString();
                    url = new URL(URLKTDK);
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
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                String KetQuaEmail = jsonObject1.getString("KetQuaEmail");
                //String KetQuaSDT = jsonObject1.getString("KetQuaSDT");

                EmailKT = Integer.parseInt(KetQuaEmail);

                if(EmailKT == 0) {
                    INSERT_KHACHHANG insert_khachhang = new INSERT_KHACHHANG();
                    insert_khachhang.execute();
                    UploadHinh(this.view);
                    finish();
                }
                else
                {
                    Toast.makeText(activity,"Email đã tồn tại !!!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }


}

