package com.example.baith2.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText edName, edAuthor, edPublishDate, edPrice;
    private Spinner spPublisher;
    private Button btnUpdate, btnCancel, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        initView();

        // Hiện ra thông tin của book cần update
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        edName.setText(book.getName());
        edAuthor.setText(book.getAuthor());
        edPublishDate.setText(book.getPublishDate());
        edPrice.setText(book.getPrice());

        for (int i=0;i<spPublisher.getCount();i++){
            // Duyệt qua list item trong spinner publisher xem item nào trùng với publisher của book cần sửa thì hiện item ý được chọn trong spinner
            if (book.getPublisher().equals(spPublisher.getItemAtPosition(i))){
                spPublisher.setSelection(i);
                break;
            }
        }
        // Bấm vào ed publisher thì hiện dialog chọn ngày
        edPublishDate.setOnClickListener(v->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    edPublishDate.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        });

        // Bấm nút update để đóng gói thông tin trên form vào 1 đối tượng Book rồi lưu xuống cơ sở dữ liệu
        //  Và trở về main activity

        btnUpdate.setOnClickListener(v->{
            String name = edName.getText().toString();
            String author = edAuthor.getText().toString();
            String publishDate = edPublishDate.getText().toString();
            String publisher = spPublisher.getSelectedItem().toString();
            String price = edPrice.getText().toString();

            Book bookUpdate = new Book(book.getId(),name,author,publishDate,publisher,price);
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.updateBook(bookUpdate);

            Intent intent1 = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intent1);
        });

        btnDelete.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Xoa");
            builder.setMessage("Co chac muon xoa?");
            builder.setPositiveButton("yes",(dialog, which) -> {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
                sqLiteHelper.deleteBook(book.getId());

                Intent intent1 = new Intent(UpdateDeleteActivity.this, MainActivity.class);
                startActivity(intent1);
            });

            builder.setNegativeButton("No", (dialog, which) -> {

            });

            AlertDialog dialog = builder.create();
            dialog.show();

        });

        btnCancel.setOnClickListener(v->{
            Intent intent1 = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edAuthor = findViewById(R.id.edAuthor);
        edPublishDate = findViewById(R.id.edPublishDate);
        edPrice = findViewById(R.id.edPrice);
        spPublisher = findViewById(R.id.spinnerPublisher);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spPublisher.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.publisher)));
    }
}