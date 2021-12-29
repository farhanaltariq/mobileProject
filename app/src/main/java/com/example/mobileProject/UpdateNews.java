package com.example.mobileProject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.atomic.AtomicReference;

public class UpdateNews extends Activity {
    DataHelper dbcenter;
    Cursor cursor;
    EditText title, category, writer, content;
    Button save, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_news);
        String key = getIntent().getStringExtra("title");

        title = findViewById(R.id.title);
        category = findViewById(R.id.category);
        writer = findViewById(R.id.writer);
        content = findViewById(R.id.content);

        // Read Databases
        dbcenter = new DataHelper(this);
        AtomicReference<String> query = new AtomicReference<>("SELECT * FROM news WHERE title = '" + key + "';");
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery(query.get(), null);
        int id = 0;
        //Write Databases to layout
        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();
            title.setText(cursor.getString(2));
            writer.setText(cursor.getString(1));
            category.setText(cursor.getString(3));
            content.setText(cursor.getString(4));
            id = cursor.getInt(0);
        }

        //Save button action
        save = findViewById(R.id.saveButton);
        int finalId = id;
        save.setOnClickListener(view -> {
            String title_text = title.getText().toString();
            String writer_text = writer.getText().toString();
            String category_text = category.getText().toString();
            String content_text = content.getText().toString();

            String sql = "UPDATE news SET title = '"
                    + title_text + "', writer = '"
                    + writer_text + "', category = '"
                    + category_text + "', news = '"
                    + content_text + "' WHERE id = "
                    + finalId + ";";
            db.execSQL(sql);
            Menu.ma.refreshList();
            finish();
        });

        // Cancel Button Action
        cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(view -> finish());
    }

}
