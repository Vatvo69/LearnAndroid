package com.example.baith2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baith2.models.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String dbName = "bookDB";

    public SQLiteHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE books(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "author TEXT," +
                "publishDate TEXT," +
                "publisher TEXT," +
                "price REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Book> getAllBook(){
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books",null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getFloat(5)+"";
            Book book = new Book(id,name,author,publishDate,publisher,price);
            bookList.add(book);
        }
        sqLiteDatabase.close();
        return bookList;
    }

    public long addBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",book.getName());
        contentValues.put("author",book.getAuthor());
        contentValues.put("publishDate",book.getPublishDate());
        contentValues.put("publisher",book.getPublisher());
        contentValues.put("price",Float.parseFloat(book.getPrice()));
        long res = sqLiteDatabase.insert("books",null,contentValues);
        sqLiteDatabase.close();
        return res;
    }

    public long updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put("author", book.getAuthor());
        contentValues.put("publishDate", book.getPublishDate());
        contentValues.put("publisher", book.getPublisher());
        contentValues.put("price",Float.parseFloat(book.getPrice()));

        long res = sqLiteDatabase.update("books",contentValues,"_id=?",new String[]{book.getId()+""});
        sqLiteDatabase.close();
        return res;
    }

    public long deleteBook(int bookId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long res = sqLiteDatabase.delete("books","_id=?",new String[]{bookId+""});
        sqLiteDatabase.close();
        return  res;
    }

    public List<Book> findBookByPrice(String startPrice, String endPrice){
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books",null,"price BETWEEN ? AND ?",new String[]{startPrice, endPrice},null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getFloat(5)+"";
            Book book = new Book(id,name,author,publishDate,publisher,price);
            bookList.add(book);
        }
        sqLiteDatabase.close();
        return bookList;
    }

    public List<Book> getStatistic(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<Book> bookList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("books",new String[]{"_id","name","author","publishDate","publisher", "MAX(price) AS price"},null,null,"publisher", null, "price DESC");
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getFloat(5)+"";
            Book book = new Book(id,name,author,publishDate,publisher,price);
            bookList.add(book);
        }
        return bookList;
    }
}
