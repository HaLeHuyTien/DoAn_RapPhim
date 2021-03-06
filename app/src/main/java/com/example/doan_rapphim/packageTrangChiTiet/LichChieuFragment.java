package com.example.doan_rapphim.packageTrangChiTiet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_rapphim.R;
import com.example.doan_rapphim.packageTrangChiTiet.packageThanhToan.ThongTinSoDoGhe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;


public class LichChieuFragment extends Fragment {

    private String jsonURL;
    private String value;

    private TrangChiTiet trangChiTiet;


    private EditText EditNgay;

    private ImageButton imgLich;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    //recyclerview
    private final LinkedList<LichChieu_Json> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private LichChieuListAdapter mAdapter;


    public static LichChieuFragment getInstance() {
        LichChieuFragment lichChieuFragment = new LichChieuFragment();
        return lichChieuFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lich_chieu_phim, container, false);

        trangChiTiet = (TrangChiTiet) getActivity();

        value = "http://0306181355.pixelcent.com/rapphim/public/api/XuatChieuTheoPhim/" + trangChiTiet.getIdPhim().toString();


        EditNgay = view.findViewById(R.id.txtLichChieu);
        imgLich = view.findViewById(R.id.imgLichChieu);


        mRecyclerView = view.findViewById(R.id.recycler_view_lich_chieu);


        imgLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XemNgay();
            }
        });

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        DateFormat df = new SimpleDateFormat("d-M-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        EditNgay.setText(date);


        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = df2.format(Calendar.getInstance().getTime());

        jsonURL = value + "/" + date2;
        ThongTinSoDoGhe.NgayChieuPhim = date2;

        GetXuatChieu getXuatChieu = new GetXuatChieu();
        getXuatChieu.execute();


        return view;

    }


    private class GetXuatChieu extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(jsonURL);
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
                mWordList.clear();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("DanhSach");
                for (int i = 0; i < jsonArray.length() / 6; i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i * 6);
                    String NgayChieu = jsonObject1.getString("NgayChieu");
                    String TenPhong = jsonObject1.getString("TenPhong");
                    String TenRap = jsonObject1.getString("TenRap");
                    Integer IDRap = jsonObject1.getInt("idrap");

                    String[] XuatChieu = new String[6];
                    Integer[] IDXuatChieu = new Integer[6];
                    Integer[] IDPhong = new Integer[6];
                    int a = 0;
                    for (int j = (i * 6); j < (i + 1) * 6; j++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                        XuatChieu[a] = jsonObject2.getString("GioChieu");
                        XuatChieu[a] = XuatChieu[a] + "/" + jsonObject2.getString("TenPhong");
                        IDXuatChieu[a] = jsonObject2.getInt("idxuatchieu");
                        IDPhong[a] = jsonObject2.getInt("idphong");
                        a++;
                    }
                    LichChieu_Json lichChieu_json = new LichChieu_Json();
                    lichChieu_json.setIDPhong(IDPhong);
                    lichChieu_json.setIDRap(IDRap);
                    lichChieu_json.setNgayChieu(NgayChieu);
                    lichChieu_json.setXuatChieu(XuatChieu);
                    lichChieu_json.setTenPhong(TenPhong);
                    lichChieu_json.setTenRap(TenRap);
                    lichChieu_json.setIDXuatChieu(IDXuatChieu);
                    if (lichChieu_json.getNgayChieu().equals(EditNgay.getText().toString())) {
                        mWordList.addLast(lichChieu_json);
                    }
                }
                mAdapter = new LichChieuListAdapter(getContext(), getActivity(), mWordList);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public void XemNgay() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                EditNgay.setText(dayOfMonth + "-" + thang + "-" + year);


                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;

                if (dayOfMonth < 10) {
                    if (thang < 10)
                        jsonURL = value + "/" + year + "-" + "0" + thang + "-" + "0" + dayOfMonth;
                    else
                        jsonURL = value + "/=" + year + "-" + thang + "-" + "0" + dayOfMonth;

                    ThongTinSoDoGhe.NgayChieuPhim = year + "-" + "0" + thang + "-" + "0" + dayOfMonth;

                } else {
                    if (thang < 10)
                        jsonURL = value + "/" + year + "-" + "0" + thang + "-" + dayOfMonth;
                    else
                        jsonURL = value + "/" + year + "-" + thang + "-" + dayOfMonth;
                    ThongTinSoDoGhe.NgayChieuPhim = year + "-" + thang + "-" + dayOfMonth;
                }
                GetXuatChieu getXuatChieu = new GetXuatChieu();
                getXuatChieu.execute();

            }
        };

        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }


}
