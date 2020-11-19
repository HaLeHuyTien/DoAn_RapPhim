package com.example.doan_rapphim.packageDangKyDangNhap.packageDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doan_rapphim.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DangKyActivity extends AppCompatActivity {
    private Button btnDangKy;
    private EditText edtHoTenDK;
    private EditText edtEmailDK;
    private EditText edtSDTDangKy;
    private EditText edtDiaChiDK;
    private EditText edtMatKhauDK;
    private EditText edtNhapLaiMatKhauDK;
    private EditText edtNgaySinh;
    private ImageButton imgLichNgaySinhFormDangKy;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy = findViewById(R.id.btnDangKyFormDK);
        edtHoTenDK = findViewById(R.id.txtHoTenDK);
        edtNgaySinh=findViewById(R.id.txtNgaySinhDK);
        edtEmailDK=findViewById(R.id.txtEmailDK);
        edtSDTDangKy=findViewById(R.id.txtSDTDangKy);
        edtDiaChiDK=findViewById(R.id.txtDiaChiDK);
        edtMatKhauDK=findViewById(R.id.txtMatKhauDK);
        edtNhapLaiMatKhauDK=findViewById(R.id.txtNhapLaiMatKhauDK);
        imgLichNgaySinhFormDangKy=findViewById(R.id.imgLichNgaySinhDK);
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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        edtNgaySinh.setText(date);
    }

    public void ChonNgaySinh(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                if(dayOfMonth > 9)
                    edtNgaySinh.setText(dayOfMonth +"/" + thang + "/" + year);
                else
                    edtNgaySinh.setText("0" + dayOfMonth + "/" + thang+ "/" + year);
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;


            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }
    public void DangKy(View view){
        String HoTen=edtHoTenDK.getText().toString();
        String Email=edtEmailDK.getText().toString();
        String SDT=edtSDTDangKy.getText().toString();
        String DiaChi=edtDiaChiDK.getText().toString();
        String MatKhau=edtMatKhauDK.getText().toString();
        String NhapLaiMatKhau=edtNhapLaiMatKhauDK.getText().toString();
        if(HoTen.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Họ và Tên cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(kiemTraNhapDung3Tu(HoTen)==true){
            Toast.makeText(this,"Bạn chưa nhập đúng 3 từ Họ và Tên trong form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(Email.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Email cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(SDT.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Số điện thoại cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(DiaChi.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Địa chỉ cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(MatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa nhập Mật khẩu cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else if(NhapLaiMatKhau.equals("")){
            Toast.makeText(this,"Bạn chưa Nhập lại mật khẩu cho form Đăng ký",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Bạn chưa nhập đúng trong form Đăng ký",Toast.LENGTH_SHORT).show();
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


}

