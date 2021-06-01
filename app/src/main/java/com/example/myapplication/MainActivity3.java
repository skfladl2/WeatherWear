package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button okButton = (Button) findViewById(R.id.ok);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText city = (EditText)findViewById(R.id.city);
                String strCity = city.getText().toString();
                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("city",strCity);
                startActivity(intent);
            }
        });
    }
}