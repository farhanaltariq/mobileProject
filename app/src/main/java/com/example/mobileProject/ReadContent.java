package com.example.mobileProject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReadContent extends Activity {
    DataHelper dbcenter;
    Cursor cursor;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        Bundle getData = getIntent().getExtras();
        String key = getData.getString("title");

        TextView title = findViewById(R.id.title);
        TextView container = findViewById(R.id.news);
        TextView writer = findViewById(R.id.writer);
        TextView category = findViewById(R.id.category);
        Button back = findViewById(R.id.back_button);

        back.setOnClickListener(view -> finish());

        dbcenter = new DataHelper(this);
        String query = "SELECT * FROM news WHERE title = '" + key + "';";
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            title.setText(cursor.getString(2));
            writer.setText("Writer\t\t\t\t\t: " + cursor.getString(1));
            category.setText("In Category : " + cursor.getString(3));
            container.setText(cursor.getString(4));
        }
    }
}
