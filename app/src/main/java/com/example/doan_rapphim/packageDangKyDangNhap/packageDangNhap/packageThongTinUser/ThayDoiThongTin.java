package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.IDUser;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThayDoiThongTin extends AppCompatActivity {
    private Spinner spnTinhTp;
    private Spinner spnHuyenQuan;
    private Spinner spnXaPhuong;
    private ArrayAdapter<QuanHuyen> spinnerListArrayAdapterQuanHuyen;
    private ArrayAdapter<PhuongXa> spinnerListArrayAdapterPhuongXa;
    private final String[] categories = {"TP Hà Nội", "TP Hồ Chí Minh", "TP Đà Nẵng"};
    public int IDThanhPhoTinh;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ImageView imageViewDoiAnh;
    private ImageButton imageButtonDate;
    private EditText editTextHoVaTen;
    private EditText editTextNgaySinh;
    private Button btnThoat;
    private Button btnLuu;
    private Button btnDoiAnh;
    private String HinhBase64;
    private ImageView imgHinhKH;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    private String DiaChi;
    public final static int PICK_IMAGE_REQUEST = 1;


    private String UpdateTT;
    private final String URLUpdateTTTK = "http://0306181355.pixelcent.com/rapphim/public/api/ThayDoiThongTinThanhVien/";
    private String LayTT;
    private final String URLLayTTTK = "http://0306181355.pixelcent.com/rapphim/public/api/ThongTinKhachHang/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);
        spnTinhTp = findViewById(R.id.spinnerThanhPho);
        spnHuyenQuan = findViewById(R.id.spinnerQuanHuyen);
        spnXaPhuong = findViewById(R.id.spinnerXaPhuong);
        imageViewDoiAnh = findViewById(R.id.imageViewDoiAnh);
        editTextHoVaTen = findViewById(R.id.editTextHVT);
        editTextNgaySinh = findViewById(R.id.editTextDateTT);
        btnLuu = findViewById(R.id.btnLuuTD);
        imgHinhKH = ((Activity) ThongTinContext.context).findViewById(R.id.imgHinhDaiDienTT1);

        initPreferences();
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

        //HienThiThongTinUser();
        HienThiThongTin hienThiThongTin = new HienThiThongTin();
        hienThiThongTin.execute();

        SpinnerView();

    }


    //Hiển thị các spinner
    public void SpinnerView() {

        //Load Dữ liệu và spinner TpTinh
        spnTinhTp.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        spnTinhTp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    IDThanhPhoTinh = position;
                    getSelectedQuanHuyen(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Load Dữ liệu và spinner QuanHuyen
        spnHuyenQuan.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getQuanHuyenList()));
        spnHuyenQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    getSelectedXaPhuong(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Loat dữ liệu XaPhuong
        spnXaPhuong.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getXaPhuongList()));

    }

    //Dữ liệu của QuanHuyen
    public ArrayList<QuanHuyen> getQuanHuyenList() {
        ArrayList<QuanHuyen> data = new ArrayList<>();
        data.clear();

        //Quận của TP.HCM
        data.add(new QuanHuyen("Quận 1", 1));
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
        data.add(new QuanHuyen("Quận Bắc Từ Liêm", 0));
        data.add(new QuanHuyen("Quận Ba Đình", 0));
        data.add(new QuanHuyen("Quận Cầu Giấy", 0));
        data.add(new QuanHuyen("Quận Đống Đa", 0));
        data.add(new QuanHuyen("Quận Hai Bà Trưng", 0));
        data.add(new QuanHuyen("Quận Hoàn Kiếm", 0));
        data.add(new QuanHuyen("Quận Hà Đông", 0));
        data.add(new QuanHuyen("Quận Hoàng Mai", 0));
        data.add(new QuanHuyen("Quận Long Biên", 0));
        data.add(new QuanHuyen("Quận Thanh Xuân", 0));
        data.add(new QuanHuyen("Quận Tây Hồ", 0));
        data.add(new QuanHuyen("Quận Nam Từ Liêm", 0));

        //Các Huyện của Đà Nẵng
        data.add(new QuanHuyen("Quận Ngũ Hành Sơn", 2));
        data.add(new QuanHuyen("Huyện Hòa Vang", 2));
        data.add(new QuanHuyen("Huyện Hoàng Sa", 2));
        data.add(new QuanHuyen("Quận Thanh Khê", 2));
        data.add(new QuanHuyen("Quận Sơn Trà", 2));
        data.add(new QuanHuyen("Quận Liên Chiểu", 2));
        data.add(new QuanHuyen("Quận Hải Châu", 2));
        data.add(new QuanHuyen("Quận Cẩm Lệ", 2));

        return data;

    }

    //Dữ liệu của Xã phường
    public ArrayList<PhuongXa> getXaPhuongList() {
        ArrayList<PhuongXa> data = new ArrayList<>();
        data.clear();

        //Phường Xã của Quận 1
        data.add(new PhuongXa("Phường Bến Nghé", 1, 0));
        data.add(new PhuongXa("Phường Bến Thành", 1, 0));
        data.add(new PhuongXa("Phường Cô Giang", 1, 0));
        data.add(new PhuongXa("Phường Cầu Kho", 1, 0));
        data.add(new PhuongXa("Phường Cầu Ông Lãnh", 1, 0));
        data.add(new PhuongXa("Phường Đa Kao", 1, 0));
        data.add(new PhuongXa("Phường Nguyễn Cư Trinh", 1, 0));
        data.add(new PhuongXa("Phường Nguyễn Thái Bình", 1, 0));
        data.add(new PhuongXa("Phường Phạm Ngũ Lão", 1, 0));
        data.add(new PhuongXa("Phường Tân Định", 1, 0));
        data.add(new PhuongXa("Phường An Khánh", 1, 0));

        //Phường Xã của Quận 2
        data.add(new PhuongXa("Phường An Khánh", 1, 1));
        data.add(new PhuongXa("Phường An Lợi Đông", 1, 1));
        data.add(new PhuongXa("Phường An Phú", 1, 1));
        data.add(new PhuongXa("Phường Bình An", 1, 1));
        data.add(new PhuongXa("Phường Bình Khánh", 1, 1));
        data.add(new PhuongXa("Phường Bình Trưng Đông", 1, 1));
        data.add(new PhuongXa("Phường Bình Trưng Tây", 1, 1));
        data.add(new PhuongXa("Phường Cát Lái", 1, 1));
        data.add(new PhuongXa("Phường Thạch Mỹ Lợi", 1, 1));
        data.add(new PhuongXa("Phường Thảo Điền", 1, 1));
        data.add(new PhuongXa("Phường Thủ Khiêm", 1, 1));

        //Phường Xã của Quận Bắc Từ Liêm
        data.add(new PhuongXa("Phường Cổ Nhuế 1", 0, 0));
        data.add(new PhuongXa("Phường Cổ Nhuế 2", 0, 0));
        data.add(new PhuongXa("Phường Đức Thắng", 0, 0));
        data.add(new PhuongXa("Phường Đông Ngạc", 0, 0));
        data.add(new PhuongXa("Phường Thụy Phương", 0, 0));
        data.add(new PhuongXa("Phường Liên Mạc", 0, 0));
        data.add(new PhuongXa("Phường Thượng Cát", 0, 0));
        data.add(new PhuongXa("Phường Tây Tựu", 0, 0));
        data.add(new PhuongXa("Phường Minh Khai", 0, 0));
        data.add(new PhuongXa("Phường Phú Diễn", 0, 0));
        data.add(new PhuongXa("Phường Xuân Đỉnh", 0, 0));
        data.add(new PhuongXa("Phường Xuân Tảo", 0, 0));

        // Phường Xã của Quận Ba Đình
        data.add(new PhuongXa("Phường Cống Vị", 0, 1));
        data.add(new PhuongXa("Phường Điện Biên", 0, 1));
        data.add(new PhuongXa("Phường Đội Cấn", 0, 1));
        data.add(new PhuongXa("Phường Giảng Võ", 0, 1));
        data.add(new PhuongXa("Phường Kim Mã", 0, 1));
        data.add(new PhuongXa("Phường Liễu Giai", 0, 1));
        data.add(new PhuongXa("Phường Ngọc Hà", 0, 1));
        data.add(new PhuongXa("Phường Ngọc Khánh", 0, 1));
        data.add(new PhuongXa("Phường Nguyễn Trung Trực", 0, 1));
        data.add(new PhuongXa("Phường Phúc Xá", 0, 1));
        data.add(new PhuongXa("Phường Quán Thánh", 0, 1));
        data.add(new PhuongXa("Phường Thành Công", 0, 1));
        data.add(new PhuongXa("Phường Trúc Bạch", 0, 1));
        data.add(new PhuongXa("Phường Vĩnh Phúc", 0, 1));

        //Phường Xã của Quận Ngũ Hành Sơn
        data.add(new PhuongXa("Phường Hòa Hải", 2, 0));
        data.add(new PhuongXa("Phường Hòa Quý", 2, 0));
        data.add(new PhuongXa("Phường Hòa Khuê Mỹ", 2, 0));
        data.add(new PhuongXa("Phường Mỹ An", 2, 0));

        //Phường Xã của Quận Hòa Vang
        data.add(new PhuongXa("Phường Hòa Châu", 2, 1));
        data.add(new PhuongXa("Phường Hòa Tiến", 2, 1));
        data.add(new PhuongXa("Phường Hòa Phước", 2, 1));
        data.add(new PhuongXa("Phường Hòa Phong", 2, 1));

        return data;
    }

    //Add DL vào spinner QuanHuyen
    public void getSelectedQuanHuyen(int IDThanhPho) {
        ArrayList<QuanHuyen> quanHuyens = new ArrayList<>();
        for (QuanHuyen quanHuyen : getQuanHuyenList()) {
            if (quanHuyen.getIDThanhPhoTinh() == IDThanhPho) {
                quanHuyens.add(quanHuyen);

            }
        }

        spinnerListArrayAdapterQuanHuyen = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quanHuyens);

        spnHuyenQuan.setAdapter(spinnerListArrayAdapterQuanHuyen);
    }


    //Add DL vào spinner XaPhuong,,,
    public void getSelectedXaPhuong(int IDQuanHuyen) {
        ArrayList<PhuongXa> phuongXas = new ArrayList<>();


        for (PhuongXa phuongXa : getXaPhuongList()) {
            if (phuongXa.getIDQuanHuyen() == IDQuanHuyen && phuongXa.getIDThanhPhoTinh() == IDThanhPhoTinh) {
                phuongXas.add(phuongXa);
            }
        }
        spinnerListArrayAdapterPhuongXa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phuongXas);

        spnXaPhuong.setAdapter(spinnerListArrayAdapterPhuongXa);
    }


    //Chọn ngày sinh
    private void ChonNgaySinh() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                if (dayOfMonth > 9)
                    editTextNgaySinh.setText(year + "-" + thang + "-" + dayOfMonth);
                else
                    editTextNgaySinh.setText(year + "-" + thang + "-" + "0" + dayOfMonth);
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;


            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }


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
        if (editTextHoVaTen.getText().toString().equals("") || editTextNgaySinh.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập đủ thông tin !", Toast.LENGTH_SHORT).show();
        } else if (editTextHoVaTen.getText().length() < 3) {
            Toast.makeText(this, "Họ và tên phải từ ít nhất 3 ký tự, tối đa 20 ký tự !", Toast.LENGTH_SHORT).show();
        } else if (editTextHoVaTen.getText().length() > 20) {
            Toast.makeText(this, "Họ và tên phải từ ít nhất 3 ký tự, tối đa 20 ký tự !", Toast.LENGTH_SHORT).show();
        } else {
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
                    ChangeHinh();

                    editor.putInt("DATA1", IDUser.idUser);
                    editor.putString("DATA2", IDUser.HinhUser);
                   IDUser.idUser = IDUser.idUser;
                   IDUser.HinhUser = IDUser.HinhUser;
                    editor.commit();


                    TextView txtHoTen = ((Activity) ThongTinContext.context).findViewById(R.id.txtHoVaTenTT);
                    TextView txtNgaySinh = ((Activity) ThongTinContext.context).findViewById(R.id.txtNgaySinhTT);
                    TextView txtDiaChi = ((Activity) ThongTinContext.context).findViewById(R.id.txtDiaChiTT);
                    txtHoTen.setText(editTextHoVaTen.getText().toString());
                    txtNgaySinh.setText(editTextNgaySinh.getText().toString());
                    txtDiaChi.setText(spnXaPhuong.getSelectedItem().toString() + ", " + spnHuyenQuan.getSelectedItem().toString() + ", " + spnTinhTp.getSelectedItem().toString());

                    Toast.makeText(ThayDoiThongTin.this, "Thay đổi thành công !", Toast.LENGTH_SHORT).show();
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
    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a file to Upload"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            imageViewDoiAnh.setImageURI(uri);
            imgHinhKH.setImageURI(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageViewDoiAnh.setDrawingCacheEnabled(true);
            Bitmap bitmap = imageViewDoiAnh.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            HinhBase64 = imageString;
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String Hinh = jsonObject1.getString("Hinh");
                    String HoTen = jsonObject1.getString("HoTen");
                    String NgaySinh = jsonObject1.getString("NgaySinh");
                    String SDT = jsonObject1.getString("SDT");

                    Picasso.get().load("http://0306181355.pixelcent.com/rapphim/public/images/" + Hinh+"1").into(imageViewDoiAnh);
                    editTextHoVaTen.setText(HoTen);
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

                DiaChi = spnXaPhuong.getSelectedItem().toString() + ", " + spnHuyenQuan.getSelectedItem().toString() + ", " + spnTinhTp.getSelectedItem().toString();

                try {
                    UpdateTT = URLUpdateTTTK + editTextHoVaTen.getText().toString() +
                            "/" + editTextNgaySinh.getText().toString() +
                            "/" + DiaChi +
                            "/" + IDUser.idUser.toString();
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }

    public void ChangeHinh() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://0306181355.pixelcent.com/rapphim/public/api/addhinh",
                response -> {
                }, error -> {
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hinh", HinhBase64);
                TextView txtSDT = ((Activity) ThongTinContext.context).findViewById(R.id.txtSDTTT);
                String a = txtSDT.getText().toString();
                params.put("TenHinh",a);
                return params;
            }
        };
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

}