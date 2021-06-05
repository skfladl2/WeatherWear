package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements Button.OnClickListener{

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.i("on","onCreate");
        Toast.makeText(this, "onCrate", Toast.LENGTH_SHORT).show();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("on","onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
    switch (v.getId()) {
        case R.id.button1: Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);startActivity(intent1); break;
        case R.id.button2: Intent intent2 = new Intent(MainActivity2.this, MainActivity4.class);startActivity(intent2); break;
        case R.id.button3: Intent intent3 = new Intent(MainActivity2.this, MainActivity5.class);startActivity(intent3); break;
    }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("on","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("on","onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("on","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("on","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("on","onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }


}