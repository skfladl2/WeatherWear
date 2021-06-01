package com.example.weatherappp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import android.os.Bundle;
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

public class WeatherInformation extends AppCompatActivity {
    String City = "London";
    String Key = "2a20d2a71c5a0737f88861d43949d630";
    String url = "http://api.openweathermap.org/data/2.5/weather?q=" + City + "&units=metric&appid=" + Key;

    public class DownloadJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            String result = "";

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while (data != -1) {
                    result += (char) data;

                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    public class DownloadIcon extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;


            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }

    TextView txtCity, txtTime, txtFeelsLike, txtValueHumidity, txtPressure, txtWindSpeed, txtTemp, txtTempMINMAX;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_information);

        txtCity = findViewById(R.id.txtCity);
        txtTime = findViewById(R.id.txtTime);
        txtFeelsLike = findViewById(R.id.txtValuefeelslike);
        txtValueHumidity = findViewById(R.id.txtValueHumidity);
        txtPressure = findViewById(R.id.txtValuePressure);
        txtWindSpeed = findViewById(R.id.txtValueWindSpeed);
        txtTemp = findViewById(R.id.txtValue);
        txtTempMINMAX = findViewById(R.id.txtTempMINMAX);
        imageView = findViewById(R.id.imgIcon);


        DownloadJSON downloadJSON = new DownloadJSON();

        try {
            String result = downloadJSON.execute(url).get();

            JSONObject jsonObject = new JSONObject(result);
            String temp = jsonObject.getJSONObject("main").getString("temp");
            String feels_like = jsonObject.getJSONObject("main").getString("feels_like");
            String humidity = jsonObject.getJSONObject("main").getString("humidity");
            String windspeed = jsonObject.getJSONObject("wind").getString("speed");
            String pressure = jsonObject.getJSONObject("main").getString("pressure");
            String temp_min = jsonObject.getJSONObject("main").getString("temp_min");
            String temp_max = jsonObject.getJSONObject("main").getString("temp_max");
            Long time = jsonObject.getLong("dt");
            String sTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA)
                    .format(new Date(time* 1000));

            txtTime.setText(sTime);
            txtCity.setText(City);
            txtFeelsLike.setText(feels_like + "°");
            txtPressure.setText(pressure + "hPa");
            txtWindSpeed.setText(windspeed + "m/s");
            txtValueHumidity.setText(humidity + "%");
            txtTemp.setText(temp + "°");
            txtTempMINMAX.setText("최저 : " + temp_min + "° " + "최고 : " + temp_max + "°");

            String nameIcon = "10d";
            nameIcon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
            String urlIcon = "http://openweathermap.org/img/wn/" + nameIcon + "@2x.png";
            DownloadIcon downloadIcon = new DownloadIcon();
            Bitmap bitmap = downloadIcon.execute(urlIcon).get();
            imageView.setImageBitmap(bitmap);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}