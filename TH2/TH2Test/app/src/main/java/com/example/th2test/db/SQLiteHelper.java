package com.example.th2test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2test.models.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String dbName = "db";

    public SQLiteHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE test(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "publishDate TEXT," +
                "publisher TEXT," +
                "cb_coop INTEGER," +
                "price REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS test");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<KhoaHoc> getAll(){
        List<KhoaHoc> khoaHocList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("test",null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String publishDate = cursor.getString(2);
            String publisher = cursor.getString(3);
            Boolean cb_book = cursor.getInt(4) != 0;
            String price = cursor.getFloat(5)+"";
            KhoaHoc khoaHoc = new KhoaHoc(name,publishDate,publisher,price,cb_book);
            khoaHocList.add(khoaHoc);
        }
        sqLiteDatabase.close();
        return khoaHocList;
    }

    public long add(KhoaHoc khoaHoc){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",khoaHoc.getName());
        contentValues.put("publishDate",khoaHoc.getPublishDate());
        contentValues.put("publisher",khoaHoc.getPublisher());
        contentValues.put("cb_coop",khoaHoc.isCb_coop());
        contentValues.put("price",Float.parseFloat(khoaHoc.getPrice()));
        long res = sqLiteDatabase.insert("test",null,contentValues);
        sqLiteDatabase.close();
        return res;
    }
}
