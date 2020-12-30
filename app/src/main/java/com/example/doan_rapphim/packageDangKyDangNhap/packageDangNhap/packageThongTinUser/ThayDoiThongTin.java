package com.example.doan_rapphim.packageDangKyDangNhap.packageDangNhap.packageThongTinUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_rapphim.MainActivity;
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


    private ImageButton imageButtonDoiAnh;
    private ImageButton imageButtonDate;
    private EditText editTextHoVaTen;
    private EditText editTextSDT;
    private EditText editTextNgaySinh;
    private Button btnThoat;
    private Button btnLuu;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;



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
        imageButtonDoiAnh = findViewById(R.id.imageButtonDoiAnh);
        editTextHoVaTen = findViewById(R.id.editTextHVT);
        editTextSDT = findViewById(R.id.editTextSDTTT);
        editTextNgaySinh = findViewById(R.id.editTextDateTT);
        btnLuu = findViewById(R.id.btnLuuTD);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LuuThayDoi(v);
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

        chonTP();
        chonQuan();
        chonPhuong();


        //HienThiThongTinUser();
        HienThiThongTin hienThiThongTin = new HienThiThongTin();
        hienThiThongTin.execute();

    }


    /*private void HienThiThongTinUser() {
        try {
            ThongTinUser thongTinUser = ReadThongTinUserJson.readThongTinUserFile(this, IDUser.idUser);
            txtHoVaTen.setText(thongTinUser.getHoVaTen());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/

    //Chọn ngày sinh
    public void ChonNgaySinh(){
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

    public void chonQuan() {
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
    }

    public void chonPhuong() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        list.add("Đà nẵng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnXaPhuong.setAdapter(adapter);
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
    public void LuuThayDoi(View view) {
        getThayDoiThongTin getThayDoiThongTin = new getThayDoiThongTin();
        getThayDoiThongTin.execute();


        Toast.makeText(this,"Lưu thay đổi thành công !",Toast.LENGTH_SHORT).show();
        finish();
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

                    int resID =getResources().getIdentifier(Hinh,"drawable",getPackageName());
                    imageButtonDoiAnh.setImageResource(resID);
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