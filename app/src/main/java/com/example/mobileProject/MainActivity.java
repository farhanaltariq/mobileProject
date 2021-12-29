package com.example.mobileProject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    DataHelper dbcenter;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbcenter = new DataHelper(this);
        // Buat captcha
        Random random = new Random();
        int cp1 = random.nextInt(10);
        int cp2 = random.nextInt(10);
        // Tampilkan layout login
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        Button login = findViewById(R.id.button);
        EditText captcha = findViewById(R.id.captcha);
        captcha.setHint(cp1+" + "+cp2);
        // Klik login
        login.setOnClickListener(view -> {
            EditText username = findViewById(R.id.editTextTextPersonName4);
            EditText password = findViewById(R.id.editTextTextPersonName5);

            String uname = username.getText().toString();
            String passwd = password.getText().toString();
            int cpc;
            try {
                cpc = Integer.parseInt(captcha.getText().toString());
            } catch (Exception e){
                cpc = 0;
            }
            //Validasi captcha
            if (cpc == cp1 + cp2)
                loggedin(uname, passwd,  cp1, cp2, cpc);
            else
                Toast.makeText(getApplicationContext(), "Captcha Incorrect", Toast.LENGTH_SHORT).show();
        });
        //Klik signup
        Button signup = findViewById(R.id.button2);
        signup.setOnClickListener(View->{
            Intent sign_up = new Intent(this, SignUp.class);
            startActivity(sign_up);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void visitCreator(){
        Intent visit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.f4lstudio.my.id"));
        startActivity(visit);
    }
    public void toFragment(){
            Intent toFragment = new Intent(MainActivity.this, Fragment.class);
            startActivity(toFragment);
    }
    void useWebService(){
        Intent useWebService = new Intent(this, UseWebService.class);
        startActivity(useWebService);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.visitCreator:
                visitCreator();
                return true;
            case R.id.toFragment:
                toFragment();
                return true;
            case R.id.useWebService:
                useWebService();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loggedin(String uname, String passwd, int cp1, int cp2, int cpc) {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" + uname + "' AND password = '" + passwd + "';", null);
        // Cek username dan password dari database
        if (cursor.getCount()>0){
            Toast.makeText(getApplicationContext(), "Successfuly Logged In", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(MainActivity.this, Menu.class);
            login.putExtra("cp1", cp1);
            login.putExtra("cp2", cp2);
            login.putExtra("cpc", cpc);
            startActivity(login);
        }
        else
            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
    }

}

