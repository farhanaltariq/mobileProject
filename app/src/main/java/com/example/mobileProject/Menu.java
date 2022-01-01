package com.example.mobileProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Menu extends Activity {
    DataHelper dbcenter;
    Cursor cursor;
    public static Menu ma;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Bundle getData = getIntent().getExtras();
        int cp1 = getData.getInt("cp1");
        int cp2 = getData.getInt("cp2");
        String text = "Captcha : " + (cp1+cp2) + "\n\nSuccessfuly Logged In";
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        Button setting = findViewById(R.id.settingButton);
        setting.setOnClickListener(view -> toSetting());

        Button addNews = findViewById(R.id.addButton);
        addNews.setOnClickListener(view -> addNews());

        Button mapButton = findViewById(R.id.mapButton);
        mapButton.setOnClickListener(view -> openMap());
        ma = this;
        dbcenter = new DataHelper(this);
        refreshList();
    }
    protected void openMap(){
        Intent openMap = new Intent(this, MapsActivity.class);
        startActivity(openMap);
    }

    public void refreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM news", null);
        cursor.moveToFirst();
        String[] list = new String[cursor.getCount()];
        for (int cc=0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            list[cc] = cursor.getString(2);
        }
        ListView listView = findViewById(R.id.data);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        listView.setSelected(true);
        listView.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            final String selection = list[arg2];
            final CharSequence[] dialogItem = {"See News", "Update News", "Delete News"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Selection");
            builder.setItems(dialogItem, (dialog, item) -> {
                switch (item){
                    case 0 :
                        Intent i = new Intent(this, ReadContent.class);
                        i.putExtra("title", selection);
                        startActivity(i);
                        break;
                    case 1 :
                        Intent ii = new Intent(this, UpdateNews.class);
                        ii.putExtra("title", selection);
                        startActivity(ii);
                        break;
                    case 2 :
                        SQLiteDatabase db1 = dbcenter.getWritableDatabase();
                        db1.execSQL("delete from news where title = '"+selection+"'");
                        refreshList();
                        break;
                }
            });
            builder.create().show();
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    //Arahkan untuk membuat berita baru
    protected void addNews(){
        Intent addNws = new Intent(this, AddNews.class);
        startActivity(addNws);
    }

    //Arahkan ke menu
    public void toMenu(View view){
        setContentView(R.layout.menu);
    }

    //Ke widget menu (Setting)
    public void toSetting(){
        Intent setting = new Intent(this, Setting.class);
        startActivity(setting);
    }

}
