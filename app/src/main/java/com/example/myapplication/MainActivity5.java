package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {
    TextView location;
    TextView time;
    TextView temperature;
    TextView sentence;
    ImageView clothes1;
    ImageView clothes2;
    TextView clothes1text;
    TextView clothes2text;

    public class Data{
        String time;
        String location;
        Double temperature;
    }



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra("city");
        String sTime = intent1.getStringExtra("time");
        Double temp = Double.parseDouble(intent1.getStringExtra("temp"));





        location = findViewById(R.id.location);
        time = findViewById(R.id.time);
        temperature=findViewById(R.id.temperature);
        clothes1=findViewById(R.id.clothes1);
        clothes2=findViewById(R.id.clothes2);
        sentence=findViewById(R.id.sentence);
        clothes1text = findViewById(R.id.clothes1text);
        clothes2text = findViewById(R.id.clothes2text);

        Data info = new Data();
        info.time = sTime;
        info.location = str;
        info.temperature = temp;

        time.setText(info.time);
        location.setText(info.location);

        temperature.setText(info.temperature + "°");

        if(info.temperature>25){
            sentence.setText("\"날씨가 더우니 \n 얇게 입는 것을 추천해요!\"");
            clothes1.setImageResource(R.drawable.shortsleeve);
            clothes2.setImageResource(R.drawable.shorts);
            clothes1text.setText("반팔");
            clothes2text.setText("반바지");

        }
        else if(12< info.temperature && info.temperature<=25) {
            sentence.setText("\"따뜻한 날씨에요!\"");
            clothes1.setImageResource(R.drawable.blouse);
            clothes2.setImageResource(R.drawable.longsleeve);
            clothes1text.setText("블라우스");
            clothes2text.setText("얇은 긴팔");
        }
        else if(5< info.temperature && info.temperature<=12){
            sentence.setText("\"조금 쌀쌀하니\n 겉옷을 챙기는 것을 추천드려요!\"");
            clothes1.setImageResource(R.drawable.outer);
            clothes2.setImageResource(R.drawable.outer1);
            clothes1text.setText("가디건");
            clothes2text.setText("자켓");

        }
        else if(info.temperature<=5){
            sentence.setText("\"밖이 추우니 \n 따뜻하게 입는 것이 좋을 것 같아요!\"");
            clothes1.setImageResource(R.drawable.coat);
            clothes2.setImageResource(R.drawable.jumper);
            clothes1text.setText("두꺼운 코트");
            clothes2text.setText("패딩");
        }

    }
}