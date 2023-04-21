package com.example.c9_sqlite_projectnew.process;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c9_sqlite_projectnew.R;
import com.example.c9_sqlite_projectnew.data.db.SQLiteHelper;
import com.example.c9_sqlite_projectnew.data.model.Item;

public class EditActivity extends AppCompatActivity {

    Item item;
    EditText etTitle, etCategory, etPrice;
    Button btUpdate, btDelete, btCancel;
    Spinner spStatus,spStatus1;
    CheckBox cbCoop;

    private final String[] statusList = {
            "Chưa hoàn thành", "Đang làm", "Đã hoàn thành"
    };
    private final String[] status1List = {
            "abc", "ced", "xyz", "tqt",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        item = (Item) getIntent().getSerializableExtra("item");
        etTitle = findViewById(R.id.et_title);
        etCategory = findViewById(R.id.et_detail);

        btUpdate = findViewById(R.id.bt_update);
        btDelete = findViewById(R.id.bt_delete);
        spStatus = findViewById(R.id.sp_status);
        spStatus1 = findViewById(R.id.sp_status1);
        cbCoop = findViewById(R.id.cb_coop);
        btCancel = findViewById(R.id.bt_cancel);

        initView();
        initListener();

    }

    private void initView() {
        etTitle.setText(item.getName());
        etCategory.setText(item.getDetail());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        for (int i =0; i <statusList.length; i++){
            if(item.getStatus() == statusList[i]){
                spStatus.setSelection(i);
                break;
            }
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                status1List);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus1.setAdapter(adapter1);

        for (int i =0; i <status1List.length; i++){
            if(item.getStatus1() == status1List[i]){
                spStatus1.setSelection(i);
                break;
            }
        }


    }

    private void initListener() {
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etTitle.getText().toString();
                String detail = etCategory.getText().toString();
                String status = spStatus.getSelectedItem().toString();
                String status1 = spStatus1.getSelectedItem().toString();;

                boolean isCoop = cbCoop.isChecked();

                if(name.isEmpty() || detail.isEmpty() || status.isEmpty() || status1.isEmpty()){
                    Toast.makeText(getBaseContext(), "Not Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    item.setName(name);
                    item.setDetail(detail);
                    item.setStatus(status);
                    item.setStatus1(status1);

                    item.setCoop(isCoop);
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                    sqLiteHelper.updateItem(item);
                    Toast.makeText(getBaseContext(), "Successfully Edit!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                sqLiteHelper.deleteItem(item.getId());
                Toast.makeText(getBaseContext(), "Successfully Delete!", Toast.LENGTH_SHORT).show();
                finish();
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