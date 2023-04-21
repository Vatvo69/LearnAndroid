package com.example.th2test.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.th2test.MainActivity;
import com.example.th2test.R;
import com.example.th2test.db.SQLiteHelper;
import com.example.th2test.models.KhoaHoc;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText edName, edPublishDate, edPrice;
    Spinner spinnerPublisher;
    CheckBox cb_coop;

    private Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        edPublishDate.setOnClickListener(v->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month++;
                    String date = dayOfMonth+"/"+month+"/"+year;
                    edPublishDate.setText(date);
                }
            },year,month,day);
            datePickerDialog.show();
        });

        btnAdd.setOnClickListener(v->{
            String name = edName.getText().toString();
            String date = edPublishDate.getText().toString();
            String publisher = spinnerPublisher.getSelectedItem().toString();
            Boolean check = cb_coop.isChecked();
            String price = edPrice.getText().toString();
            KhoaHoc khoaHoc = new KhoaHoc(name,date,publisher,price,check);
            SQLiteHelper sqLiteHelper = new SQLiteHelper(AddActivity.this);
            sqLiteHelper.add(khoaHoc);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edPublishDate = findViewById(R.id.edPublishDate);
        edPrice = findViewById(R.id.edPrice);
        spinnerPublisher = findViewById(R.id.spinnerPublisher);
        spinnerPublisher.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,getResources().getStringArray(R.array.publisher)));
        cb_coop = findViewById(R.id.cb_coop);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

    }
}