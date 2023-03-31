package com.example.th1_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.th1_final.adapter.AdapterVe;
import com.example.th1_final.model.MayBay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText maVe,loaiVe,ngay;
    RadioGroup radioGroup;
    RadioButton radioButtonVIP,radioButtonTT,radioButtonGR;
    CheckBox checkBox;
    Button btnAdd,btnUpdate,btnDelete;

    SearchView searchView;
    RecyclerView recyclerView;

    int currentPosition;

    AdapterVe adapterVe;

    List<MayBay> mayBayList;
    List<MayBay> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mayBayList = new ArrayList<>();
        mayBayList.add(new MayBay("VN123","VIP","1/1/2001",true));
        mayBayList.add(new MayBay("VN456","THONGTHUONG","2/2/2002",false));
        List<String> listMaVe = new ArrayList<>();
        List<String> listLoaiVe = new ArrayList<>();
        List<String> listNgay = new ArrayList<>();
        List<Boolean> listCheckBox = new ArrayList<>();
        for (MayBay mayBay: mayBayList){
            listMaVe.add(mayBay.getMaVe());
            listLoaiVe.add(mayBay.getLoaiVe());
            listNgay.add(mayBay.getNgayBay());
            listCheckBox.add(mayBay.isCheckBox());
        }

        initview();
        handlerNgay();

        adapterVe = new AdapterVe(listMaVe,listLoaiVe,listNgay,listCheckBox);
        adapterVe.setOnMyItemClickListener(position->{
            currentPosition = position;
            maVe.setText(adapterVe.listMaVe.get(currentPosition));
            if(adapterVe.listLV.get(position) == MayBay.VIP){
                radioButtonVIP.isChecked();
            }
            else if(adapterVe.listLV.get(position)==MayBay.PHOTHONG){
                radioButtonTT.isChecked();
            }
            else radioButtonGR.isChecked();
            ngay.setText(adapterVe.listNgay.get(currentPosition));
            checkBox.setChecked(adapterVe.listCheckBox.get(currentPosition));
            toggleEditButton();
        });
        recyclerView.setAdapter(adapterVe);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        handleAddClick();
        handleUpdateClick();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AdapterVe adapterVe1;
                List<String> ve= new ArrayList<>(),loai = new ArrayList<>(),ngay= new ArrayList<>();
                List<Boolean> check = new ArrayList<>();
                searchList = new ArrayList<>();
                if (newText.length()>0){
                    for (int i=0;i<listMaVe.size();i++){
                        if (listMaVe.get(i).toUpperCase(Locale.ROOT).contains(newText.toUpperCase(Locale.ROOT))){
                            ve.add(adapterVe.listMaVe.get(i));
                            loai.add(adapterVe.listLV.get(i));
                            ngay.add(adapterVe.listNgay.get(i));
                            check.add(adapterVe.listCheckBox.get(i));
                        }
                    }
                    adapterVe1 = new AdapterVe(ve,loai,ngay,check);
                    recyclerView.setAdapter(adapterVe1);
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                }
                else{
                    recyclerView.setAdapter(adapterVe);
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                }
                return false;
            }
        });
    }

    private void handleUpdateClick() {
        btnUpdate.setOnClickListener(v->{
            MayBay mayBay = getDataFromForm();
            if (mayBay!=null){
               adapterVe.updateItem(currentPosition,mayBay.getMaVe(),mayBay.getLoaiVe(),mayBay.getNgayBay(),mayBay.isCheckBox());
                clearForm();
                toggleAddButton();
            }
        });
    }

    private void handleAddClick() {
        btnAdd.setOnClickListener(v->{
            MayBay mayBay = getDataFromForm();
            if (mayBay!=null){
                adapterVe.addItem(mayBay.getMaVe(),mayBay.getLoaiVe(),mayBay.getNgayBay(),mayBay.isCheckBox());
                clearForm();
            }

        });
    }

    private void clearForm() {
        maVe.setText("");
        radioButtonVIP.setChecked(true);
        ngay.setText("");
        checkBox.setChecked(false);
    }

    private MayBay getDataFromForm() {
        String mVe = maVe.getText().toString();
        String n = ngay.getText().toString();
        Boolean check = checkBox.isChecked();
        int radioId = radioGroup.getCheckedRadioButtonId();
        String lVe;
        if(radioId == R.id.radio_vip){
            lVe = MayBay.VIP;
        }
        else if(radioId == R.id.radio_tt){
            lVe = MayBay.PHOTHONG;
        }
        else{
            lVe = MayBay.GIARE;
        }
        if (mVe.isEmpty() || n.isEmpty()){
            Toast.makeText(getApplicationContext(),"Not Empty!",Toast.LENGTH_SHORT).show();
            return null;
        }
        MayBay mayBay = new MayBay(mVe,lVe,n,check);
        return mayBay;
    }


    private void toggleAddButton() {
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
    }

    private void toggleEditButton() {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
    }

    private void handlerNgay() {
        ngay.setOnClickListener(v->{
            Calendar c = Calendar.getInstance();
            int d = c.get(Calendar.DAY_OF_MONTH);
            int m = c.get(Calendar.MONTH);
            int y = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ngay.setText(dayOfMonth+"/"+month+"/"+year);
                }
            },y,m,d);
            datePickerDialog.show();
        });
    }
    private void initview() {
        maVe = findViewById(R.id.ma_ve);
        ngay = findViewById(R.id.ngay);
        radioGroup = findViewById(R.id.radio_group);
        radioButtonVIP = findViewById(R.id.radio_vip);
        radioButtonTT = findViewById(R.id.radio_tt);
        radioButtonGR = findViewById(R.id.radio_gr);
        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        checkBox = findViewById(R.id.checkbox);
        searchView = findViewById(R.id.search_view);
    }
}