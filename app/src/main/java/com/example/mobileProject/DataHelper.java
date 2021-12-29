package com.example.mobileProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user(username TEXT PRIMARY KEY, password TEXT NOT NULL);";
        Log.d("Data", "onCreate : " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO user(username, password) VALUES ('Farhan', 'Password');";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE news (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, writer TEXT NOT NULL , title TEXT NOT NULL, category TEXT NOT NULL, news TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO news(id, writer, title, category, news) VALUES" +
              "(1, 'Farhan Altariq', 'Uji Coba SQLite', 'Technology', 'Ini Uji Coba SQLite - Bagian Isi')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
