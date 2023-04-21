package com.example.c9_sqlite_projectnew.process;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c9_sqlite_projectnew.R;
import com.example.c9_sqlite_projectnew.data.db.SQLiteHelper;
import com.example.c9_sqlite_projectnew.data.model.Item;

public class AddActivity extends AppCompatActivity {

    private final String[] statusList = {
            "Chưa hoàn thành", "Đang làm", "Đã hoàn thành"
    };
    private final String[] status1List = {
            "abc", "ced", "xyz", "tqt"
    };
    EditText etTitle, etCategory, etPrice;
    Button btAdd, btCancel;
    CheckBox cbCoop;
    Spinner spStatus,spStatus1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_title);
        etCategory = findViewById(R.id.et_detail);

        btAdd = findViewById(R.id.bt_update);
        btCancel = findViewById(R.id.bt_cancel);
        cbCoop = findViewById(R.id.cb_coop);
        spStatus = findViewById(R.id.sp_status);
        spStatus1 = findViewById(R.id.sp_status1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                status1List);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus1.setAdapter(adapter1);

        initListener();
    }

    private void initListener() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etTitle.getText().toString();
                String detail = etCategory.getText().toString();
                String status = spStatus.getSelectedItem().toString();
                String status1 = spStatus1.getSelectedItem().toString();

                boolean isCoop = cbCoop.isChecked();

                if (name.isEmpty() || detail.isEmpty() || status.isEmpty() || status1.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Not Empty", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                    Item item = new Item(0, name, detail, status, status1, isCoop);
                    sqLiteHelper.addItem(item);
                    Toast.makeText(getBaseContext(), "Successfully Add!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.putExtra("item", item);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}