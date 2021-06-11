package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.io.ByteArrayOutputStream;

public class MainActivity6 extends AppCompatActivity {

    ImageView image1, image2, image3, image4;

    final int PICTURE_REQUEST_CODE = 100;
    static final int REQUEST_TAKE_ALBUM = 2002;


    public void doTakeMultiAlbumAction() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Get Album"), REQUEST_TAKE_ALBUM);
        //(MainActivity6)mContext)
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTakeMultiAlbumAction();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTakeMultiAlbumAction();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTakeMultiAlbumAction();
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTakeMultiAlbumAction();
            }
        });

        image1 = (ImageView) findViewById(R.id.img1);
        image2 = (ImageView) findViewById(R.id.img2);
        image3 = (ImageView) findViewById(R.id.img3);
        image4 = (ImageView) findViewById(R.id.img4);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "CALL");
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_ALBUM:
                Log.i("result", String.valueOf(resultCode));
                if (resultCode == Activity.RESULT_OK) {
                    ArrayList imageList = new ArrayList<>();

                    if (data.getClipData() == null) {
                        Log.i("1. single choice", String.valueOf(data.getData()));
                        imageList.add(String.valueOf(data.getData()));
                    } else {

                        ClipData clipData = data.getClipData();
                        Log.i("clipdata", String.valueOf(clipData.getItemCount()));
                        if (clipData.getItemCount() > 10){
                            Toast.makeText(MainActivity.this, "사진은 10개까지 선택가능 합니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        else if (clipData.getItemCount() == 1) {
                            String dataStr = String.valueOf(clipData.getItemAt(0).getUri());
                            Log.i("2. clipdata choice", String.valueOf(clipData.getItemAt(0).getUri()));
                            Log.i("2. single choice", clipData.getItemAt(0).getUri().getPath());
                            imageList.add(dataStr);

                        } else if (clipData.getItemCount() > 1 && clipData.getItemCount() < 10) {
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                Log.i("3. single choice", String.valueOf(clipData.getItemAt(i).getUri()));
                                imageList.add(String.valueOf(clipData.getItemAt(i).getUri()));
                            }
                        }
                    }

                    Intent intent = new Intent(MainActivity6.this, ImageView1.class);
                    intent.putStringArrayListExtra("list", imageList);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "사진 선택을 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

