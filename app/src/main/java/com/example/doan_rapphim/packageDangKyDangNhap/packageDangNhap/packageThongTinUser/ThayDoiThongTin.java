package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy.DangKyActivity;
import com.squareup.picasso.Picasso;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;
    private ArrayAdapter<QuanHuyen> spinnerListArrayAdapterQuanHuyen;
    private ArrayAdapter<PhuongXa> spinnerListArrayAdapterPhuongXa;
    private String[] categories = {"Hà Nội", "TP.HCM" , "Đà Nẵng"};

    private ImageView imageViewDoiAnh;
    private ImageButton imageButtonDate;
    private EditText editTextHoVaTen;
    private EditText editTextSDT;
    private EditText editTextNgaySinh;
    private Button btnThoat;
    private Button btnLuu;
    private Button btnDoiAnh;

    private String Hinhkt;
    private String HoVaTenkt;
    private String SDTkt;
    private String NgaySinhkt;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    private TextView txtNgayHienTai;
    public final static int PICK_IMAGE_REQUEST = 1;


    private String UpdateTT;
    private String URLUpdateTTTK = "http://0306181355.pixelcent.com/Cinema/ThayDoiThongTinThanhVien.php?HoTen=";
    private String LayTT;
    private String URLLayTTTK = "http://0306181355.pixelcent.com/Cinema/ThongTinKhachHang.php?ID=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
        spnTinhTp = findViewById(R.id.spinnerThanhPho);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyen);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuong);
        imageViewDoiAnh = findViewById(R.id.imageViewDoiAnh);
        editTextHoVaTen = findViewById(R.id.editTextHVT);
        editTextSDT = findViewById(R.id.editTextSDTTT);
        editTextNgaySinh = findViewById(R.id.editTextDateTT);
        btnLuu = findViewById(R.id.btnLuuTD);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LuuThayDoi();
            }
        });


        //Nút thoát
        btnThoat = findViewById(R.id.btnThoatTD);

        //Nút chọn ngày
        imageButtonDate = findViewById(R.id.imgButtonDate);
        imageButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgaySinh();
            }
        });

        btnDoiAnh = findViewById(R.id.btnDoiAnh);
        btnDoiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });


        //chonTP();
        //chonQuan();
        //chonPhuong();


        //HienThiThongTinUser();
        HienThiThongTin hienThiThongTin = new HienThiThongTin();
        hienThiThongTin.execute();


        SpinnerView();

    }

    DangKyActivity dangKyActivity = new DangKyActivity();
    //Hiển thị các spinner
    public void SpinnerView() {

        //Load Dữ liệu và spinner TpTinh
        spnTinhTp.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories));
        spnTinhTp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0 ) {
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
            if(phuongXa.getIDQuanHuyen()== IDQuanHuyen) {
               phuongXas.add(phuongXa);
            }
        }
        spinnerListArrayAdapterPhuongXa = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,phuongXas);

        spnXaPhuong.setAdapter(spinnerListArrayAdapterPhuongXa);
    }


    //Chọn ngày sinh
    private void ChonNgaySinh(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                if(dayOfMonth > 9)
                    editTextNgaySinh.setText(year +"-" + thang + "-" + dayOfMonth);
                else
                    editTextNgaySinh.setText( year + "-" + thang+ "-" + "0" + dayOfMonth);
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;


            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

    public void chonTP() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTinhTp.setAdapter(adapter);
    }

    /*public void chonQuan() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("Quận 1");
        list.add("Quận 2");
        list.add("Quận 3");
        list.add("Quận 4");
        list.add("Quận 5");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnHuyenQuan.setAdapter(adapter);
    }*/

    /*public void chonPhuong() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnXaPhuong.setAdapter(adapter);
    }*/

    //Nút thoát
    public void Thoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");

        // Ask the final question
        builder.setMessage("Đồng ý thoát ?");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                finish();
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    //Nút Lưu thay đổi
    public void LuuThayDoi() {
        if(editTextHoVaTen.getText().toString().equals("") || editTextNgaySinh.getText().toString().equals("") || editTextSDT.getText().toString().equals("")) {
            Toast.makeText(this,"Bạn chưa nhập đủ thông tin !",Toast.LENGTH_SHORT).show();
        }
        else if (editTextHoVaTen.getText().length()<3) {
            Toast.makeText(this,"Họ và tên phải trên 3 ký tự !",Toast.LENGTH_SHORT).show();
        }
        else if (editTextSDT.getText().length()<10 || editTextSDT.getText().length()>10) {
            Toast.makeText(this,"Số điện thoại phải đúng 10 ký tự !",Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông Báo");

            // Ask the final question
            builder.setMessage("Đồng ý lưu thay đổi thông tin ?");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button
                    // Set the TextView visibility GONE
                    getThayDoiThongTin getThayDoiThongTin = new getThayDoiThongTin();
                    getThayDoiThongTin.execute();

                    TextView txtHoTen = ((Activity) ThongTinContext.context).findViewById(R.id.txtHoVaTenTT);
                    TextView txtSDT = ((Activity) ThongTinContext.context).findViewById(R.id.txtSDTTT);
                    TextView txtNgaySinh = ((Activity) ThongTinContext.context).findViewById(R.id.txtNgaySinhTT);
                    txtHoTen.setText(editTextHoVaTen.getText().toString());
                    txtSDT.setText(editTextSDT.getText().toString());
                    txtNgaySinh.setText(editTextNgaySinh.getText().toString());

                    finish();

                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked
                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();
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
            imageViewDoiAnh.setImageURI(uri);
        }
    }


    //Hiển thị thông tin Thành Viên
    private class HienThiThongTin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                LayTT = URLLayTTTK + IDUser.idUser;
                try {
                    url = new URL(LayTT);
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

                for(int i = 0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String Hinh = jsonObject1.getString("Hinh");
                    String HoTen = jsonObject1.getString("HoTen");
                    String NgaySinh = jsonObject1.getString("NgaySinh");
                    String SDT = jsonObject1.getString("SDT");

                    Hinhkt = Hinh;
                    HoVaTenkt = HoTen;
                    NgaySinhkt = NgaySinh;
                    SDTkt = SDT;

                    Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + Hinh).into(imageViewDoiAnh);
                    //int resID =getResources().getIdentifier(Hinh,"drawable",getPackageName());
                    //imageButtonDoiAnh.setImageResource(resID);
                    editTextHoVaTen.setText(HoTen);
                    editTextSDT.setText(SDT);
                    editTextNgaySinh.setText(NgaySinh);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    //ThayDoiThongTinThanhVien
    private class getThayDoiThongTin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    UpdateTT = URLUpdateTTTK + editTextHoVaTen.getText().toString() +
                            "&SDT=" + editTextSDT.getText().toString() +
                            "&NgaySinh=" + editTextNgaySinh.getText().toString() +
                            "&id=" + IDUser.idUser.toString();
                    url = new URL(UpdateTT);

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



}