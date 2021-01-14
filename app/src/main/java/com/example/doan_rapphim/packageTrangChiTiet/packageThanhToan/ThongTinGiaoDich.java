package com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.IDPhim;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThongTinGiaoDich extends AppCompatActivity {
    private TextView txtTenPhim;
    private TextView txtPhong;
    private TextView txtSuatChieu;
    private TextView txtTenRap;
    private Button btnThayDoi;
    private Button btnXacNhan;
    private TextView txtTTGD;
    private TextView txtSL;
    private TextView txtTTGDtong;
    private String GheDaChon = "";
    private String InsertVe = "http://0306181355.pixelcent.com/Cinema/VePhim.php?IDKhachHang=";
    public Integer[] GheID = new Integer[ThongTinSoDoGhe.sl];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_giao_dich);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnThayDoi = findViewById(R.id.btnTroVe);
        txtSL = findViewById(R.id.txtTTGDSoLuong);
        txtTTGDtong = findViewById((R.id.txtTTGDTongTien));
        txtTTGD = findViewById(R.id.txtTTGDGhe);
        txtPhong = findViewById(R.id.txtTTGDPhong);
        txtSuatChieu = findViewById(R.id.txtTTGDSuatChieu);
        txtTenPhim = findViewById(R.id.txtTTGDTenPhim);
        txtTenRap = findViewById(R.id.txtTTGDTenrap);

        Hienthids();

        btnThayDoi.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(ThongTinGiaoDich.this, com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.SoDoGhe.class);
                                              startActivity(intent);
                                          }
                                      }
        );
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              for (int i = 0; i < ThongTinSoDoGhe.sl; i++) {
                                                  INSERT_VE insert_ve = new INSERT_VE(GheID[i]);
                                                  insert_ve.execute();
                                              }
                                              AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinGiaoDich.this);
                                              // Set a title for alert dialog
                                              builder.setTitle("Thông Báo");

                                              // Ask the final question
                                              builder.setMessage("ĐẶT GHẾ THÀNH CÔNG !!!");

                                              // Set the alert dialog yes button click listener
                                              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      // Do something when user clicked the Yes button
                                                      // Set the TextView visibility GONE
                                                      Intent intent = new Intent(builder.getContext(), com.example.doan_rapphim.MainActivity.class);
                                                      startActivity(intent);
                                                  }
                                              });
                                              // Set the alert dialog no button click listener


                                              AlertDialog dialog = builder.create();
                                              // Display the alert dialog on interface
                                              dialog.show();

                                          }
                                      }
        );

        Integer x = 0;
        for (int i = 0; i < 4; i++) {
            Integer hang = 0;
            for (Integer j = 0; j < 5; j++) {
                if (ThongTinSoDoGhe.Ghe[i][j] == true) {
                    String Hang = "";
                    if (i == 0) {
                        Hang = "A";
                        hang = 0;
                    }
                    if (i == 1) {
                        hang = 5;
                        Hang = "B";
                    }
                    if (i == 2) {
                        Hang = "C";
                        hang = 10;
                    }
                    if (i == 3) {
                        Hang = "D";
                        hang = 15;
                    }
                    Integer Cot = j + 1;
                    Integer idGhe = Cot + hang;
                    GheID[x] = idGhe;
                    x++;
                    GheDaChon = GheDaChon + Hang + Cot.toString() + ",";
                }
            }
        }

        String[] separated = GheDaChon.split(",");
        GheDaChon = "";
        for (int i = 0; i < separated.length; i++) {
            GheDaChon = GheDaChon + separated[i];
            if (i == separated.length - 1) {
                GheDaChon = GheDaChon + ".";
            } else {
                GheDaChon = GheDaChon + ", ";
            }
        }
        txtTTGD.setText("");
        txtTTGD.setText(GheDaChon);
    }

    private void Hienthids() {
        txtSL.setText(ThongTinSoDoGhe.sl.toString());
        txtTTGDtong.setText(ThongTinSoDoGhe.tongTien.toString());
        txtPhong.setText(ThongTinSoDoGhe.TenPhong);
        txtTenPhim.setText(ThongTinSoDoGhe.tenPhim);
        txtSuatChieu.setText(ThongTinSoDoGhe.suatChieu);
    }

    private class INSERT_VE extends AsyncTask<String, String, String> {

        Integer idGhe;
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = df2.format(Calendar.getInstance().getTime());

        public INSERT_VE(Integer idGhe) {
            this.idGhe = idGhe;
        }

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    Integer tb = ThongTinSoDoGhe.tongTien / ThongTinSoDoGhe.sl;
                    InsertVe = "http://0306181355.pixelcent.com/Cinema/VePhim.php?IDKhachHang=" + ThongTinSoDoGhe.IDKhachHang + "&IDPhim=" + IDPhim.ID + "&IDRap=" + ThongTinSoDoGhe.IDRap + "&IDPhong=" + ThongTinSoDoGhe.IDPhong + "&IDXuatChieu=" + ThongTinSoDoGhe.IDXuatChieu + "&IDGhe=" + idGhe + "&tongtien=" + tb + "&ngaydatve=" + date2 + "&ngaychieuphim=" + ThongTinSoDoGhe.NgayChieuPhim;

                    url = new URL(InsertVe);
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
}