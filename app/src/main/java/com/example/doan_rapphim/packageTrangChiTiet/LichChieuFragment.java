package com.example.doan_rapphim.packageTrangChiTiet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;


import com.example.doan_rapphim.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;




public class LichChieuFragment extends Fragment {
    private Spinner spnDiaDiem;

    private Spinner spnRap;

    private EditText EditNgay;

    private ImageButton imgLich;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    //Json
    private TextView TenRap;
    private TextView TenTinh;
    private Button btnGioChieu[] = new Button[8];

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

        spnDiaDiem = view.findViewById(R.id.spinDiaDiem);
        spnRap = view.findViewById(R.id.spinRap);
        EditNgay = view.findViewById(R.id.editNgay);
        imgLich = view.findViewById(R.id.imageLich);


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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        EditNgay.setText(date);
        addDiaDiem();
        addRap();



        HienThiDanhSach();

        spnDiaDiem.setOnItemSelectedListener(myListener);
        spnRap.setOnItemSelectedListener(myListener);


        return view;

    }

    AdapterView.OnItemSelectedListener myListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           Loc();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void Loc(){
        LinkedList<LichChieu_Json> result = new LinkedList<>();
        LinkedList<LichChieu_Json> KQ = new LinkedList<>();
        int count = mWordList.size();
        int diadiemvitri = spnDiaDiem.getSelectedItemPosition();
        String diadiemgiatri = spnDiaDiem.getSelectedItem().toString();
        int rapvitri = spnRap.getSelectedItemPosition();
        String NgayChieu = EditNgay.getText().toString();
        String rapgiatri = spnRap.getSelectedItem().toString();
        if(diadiemvitri == 0){
            if(rapvitri == 0)
            {
                KQ = mWordList;
            }
            else {
                for (int i = 0; i < count; i++) {
                    if (mWordList.get(i).getTenRap().equals(rapgiatri) && mWordList.get(i).getNgayChieu().equals(NgayChieu)) {
                        result.add(mWordList.get(i));
                    }
                }
                KQ = result;
            }
        }
        else{
            if(rapvitri == 0) {
                for (int i = 0; i < count; i++) {
                    if (mWordList.get(i).getTenTinh().equals(diadiemgiatri) && mWordList.get(i).getNgayChieu().equals(NgayChieu)) {
                        result.add(mWordList.get(i));
                    }
                }
                KQ = result;
            }
            else
            {
                for (int i = 0; i < count; i++) {
                    if (mWordList.get(i).getTenTinh().equals(diadiemgiatri) && mWordList.get(i).getTenRap().equals(rapgiatri) && mWordList.get(i).getNgayChieu().equals(NgayChieu)) {
                        result.add(mWordList.get(i));
                    }
                }
                KQ = result;
            }
        }
        mAdapter = new LichChieuListAdapter(getActivity(),KQ);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void HienThiDanhSach(){
        try {
            Integer soluongXuatChieu = ReadLichChieuJson.SoLuongXuatChieu(getActivity());
            mWordList.clear();
            for(Integer i = 0; i < soluongXuatChieu; i++) {
                LichChieu_Json lichChieu_json = ReadLichChieuJson.readLichChieuJsonFile(getActivity(), i);
                if(lichChieu_json.getNgayChieu().equals(EditNgay.getText().toString())) {
                    mWordList.addLast(lichChieu_json);
                }
            }
            mAdapter = new LichChieuListAdapter(getActivity(),mWordList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }catch (Exception e){
            EditNgay.setText("b");

        }
    }


    public void addDiaDiem() {
        List<String> list = new ArrayList<>();
        list.add("Cả nước");
        list.add("TP.Hồ Chí Minh");
        list.add("Hà Nội");
        list.add("Cà Mau");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnDiaDiem.setAdapter(adapter);
    }

    public void addRap() {
        List<String> list = new ArrayList<>();
        list.add("Tất cả rạp");
        list.add("Galaxy 1");
        list.add("Galaxy 2");
        list.add("Galaxy 3");
        list.add("Galaxy 4");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnRap.setAdapter(adapter);
    }

    public void XemNgay(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int thang = month + 1;
                if(dayOfMonth > 9)
                EditNgay.setText(dayOfMonth +"/" + thang + "/" + year);
                else
                    EditNgay.setText("0" + dayOfMonth + "/" + thang+ "/" + year);
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;
                HienThiDanhSach();

            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

}
