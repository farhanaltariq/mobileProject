package com.example.mobileProject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
    DataHelper dbcenter;
    Cursor cursor;
    protected void onCreate(Bundle savedInstanceState){
        dbcenter = new DataHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuppage);

        EditText usernameField = findViewById(R.id.uname_field);
        EditText passwordField = findViewById(R.id.password_field);
        EditText re_passwordField = findViewById(R.id.re_password);

        Button cancel = findViewById(R.id.cancel_button);
        // Kembali ke laman login
        cancel.setOnClickListener(view -> finish());

        Button signup = findViewById(R.id.signup_button);
        // Atur signup
        signup.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            String re_password = re_passwordField.getText().toString();

            String sql = "SELECT * FROM user WHERE username = '" + username + "';";
            SQLiteDatabase db = dbcenter.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            if (cursor.getCount() > 0){
                Toast.makeText(this, "Username already used", Toast.LENGTH_SHORT).show();
            } else {
                if (!password.equals(re_password) || password.isEmpty())
                    Toast.makeText(this, "Validation password incorrect", Toast.LENGTH_SHORT).show();
                else {
                    sql = "INSERT INTO user(username, password) VALUES('"+username+"', '" + password + "');";
                    db.execSQL(sql);
                    Toast.makeText(this, "Account Created Succesfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
