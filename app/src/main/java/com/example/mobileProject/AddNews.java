package com.example.mobileProject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddNews extends Activity {
    DataHelper dbcenter;

    Button save;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_news);

        EditText getTitle = findViewById(R.id.title);
        EditText getWriter = findViewById(R.id.writer);
        EditText getCategory = findViewById(R.id.category);
        EditText getNews = findViewById(R.id.news);

        dbcenter = new DataHelper(this);

        // Klik cancel
        cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(view -> finish());

        // Klik save
        save = findViewById(R.id.saveButton);
        save.setOnClickListener(view -> {
            String title = getTitle.getText().toString();
            String writer = getWriter.getText().toString();
            String category = getCategory.getText().toString();
            String news = getNews.getText().toString();
            String query = "INSERT INTO news(title, writer, category, news) VALUES('"+
                            title + "', '" + writer + "', '" + category + "','" + news+ "');";

            SQLiteDatabase db = dbcenter.getReadableDatabase();
            db.execSQL(query);
            Menu.ma.refreshList();
            finish();
        });
    }

}
