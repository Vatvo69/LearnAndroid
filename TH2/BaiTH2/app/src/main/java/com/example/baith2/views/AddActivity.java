package com.example.baith2.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baith2.MainActivity;
import com.example.baith2.R;
import com.example.baith2.db.SQLiteHelper;
import com.example.baith2.models.Book;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText edName, edAuthor, edPublishDate, edPrice;
    private Spinner spinner;
    private Button btnUpdate, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        // Bấm vào ed publisher hiển thị dialog chọn ngày
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

        // Click nut update để đóng gói thông tin trong form vào 1 đối tượng Book rồi lưu xuống csdl và trở về main activity
        btnUpdate.setOnClickListener(v->{
            String name = edName.getText().toString();
            String author = edAuthor.getText().toString();
            String publishDate = edPublishDate.getText().toString();
            String publisher = spinner.getSelectedItem().toString();
            String price = edPrice.getText().toString();

            Book book = new Book(name,author,publishDate,publisher,price);
            SQLiteHelper sqLiteHelper = new SQLiteHelper(AddActivity.this);
            sqLiteHelper.addBook(book);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edAuthor = findViewById(R.id.edAuthor);
        edPublishDate = findViewById(R.id.edPublishDate);
        edPrice = findViewById(R.id.edPrice);
        spinner = findViewById(R.id.spinnerPublisher);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.publisher)));
    }
}