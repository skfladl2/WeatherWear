package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    private TextView tCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tCity = findViewById(R.id.tCity);

        Intent intent = getIntent();
        String str = intent.getStringExtra("city");
        String strUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + str + "&appid=867d2553fb743f162631454d6ebde974&lang=kr";

        tCity.setText(str);
    }
}