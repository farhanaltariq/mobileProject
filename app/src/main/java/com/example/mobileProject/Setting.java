package com.example.mobileProject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Setting extends Activity {
    //Deklarasi checkbox
    private CheckBox sports;
    private CheckBox finance;
    private CheckBox career;
    private CheckBox tech;
    private CheckBox entertainment;
    //Deklarasi Radio
    private RadioButton small;
    private RadioButton medium;
    //Deklarasi ToggleButton
    private ToggleButton restriction;
    private ToggleButton darkmode;

    @SuppressLint("CutPasteId")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        sports = findViewById(R.id.checkbox_sports);
        finance = findViewById(R.id.checkbox_finance);
        career = findViewById(R.id.checkbox_career);
        tech = findViewById(R.id.checkbox_technology);
        entertainment = findViewById(R.id.checkbox_career);

        findViewById(R.id.textSize);
        small = findViewById(R.id.textSmall);
        medium = findViewById(R.id.textMedium);
        findViewById(R.id.textLarge);

        restriction = findViewById(R.id.restrictButton);
        darkmode = findViewById(R.id.darkModeButton);
    }
    //Proses data setting
    @SuppressLint("SetTextI18n")
    public void saveSetting(View view){
        setContentView(R.layout.setting_confirm);

        TextView newsPref = findViewById(R.id.selectedPreference);
        String text = "";
        if (sports.isChecked())
            text = text + "Sports\n";
        if (finance.isChecked())
            text = text + "Finance\n";
        if (career.isChecked())
            text = text + "Career\n";
        if (tech.isChecked())
            text = text + "Tech\n";
        if (entertainment.isChecked())
            text = text + "Entertainment\n";
        newsPref.setTextColor(Color.GRAY);
        newsPref.setText(text);

        TextView restrict = findViewById(R.id.restrictionStatus);
        restrict.setTextColor(Color.GRAY);
        if (restriction.isChecked())
            restrict.setText("ON");
        else
            restrict.setText("OFF");

        TextView darkmodes = findViewById(R.id.darkModeStatus);
        darkmodes.setTextColor(Color.GRAY);
        if (darkmode.isChecked())
            darkmodes.setText("ON");
        else
            darkmodes.setText("OFF");

        TextView font_size = findViewById(R.id.fontSizeStatus);
        font_size.setTextColor(Color.GRAY);
        if (small.isChecked())
            font_size.setText("Small");
        else if (medium.isChecked())
            font_size.setText("Medium");
        else
            font_size.setText("Large");

        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(view1 -> finish());
    }

    public void cancel(View view) {
        finish();
    }
}
