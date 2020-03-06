package com.example.abwn179.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.abwn179.myapplication.adapter.WeatherAdapter;
import com.example.abwn179.myapplication.model.WeatherModel;
import com.example.abwn179.myapplication.service.WeatherService;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    WeatherAdapter weatherAdapter;
    WeatherResponse weatherResponse;
    WeatherModel weatherModel;
    RecyclerView recyclerView;
    TextView weatherDescription;
    TextView weatherInDegrees;
    TextView textViewMin;
    TextView textViewCurrent;
    TextView textViewMax;
    TextView WeatherInDegrees;
    ArrayList<WeatherModel> list = new ArrayList<WeatherModel>();
    ArrayList<WeatherResponse> list_numberOfDays = new ArrayList<>();
    //private WeatherViewModel weatherViewModel;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "9c2cbef044b0f5e8d06942b5dd970d22";
    public static String lat = "-25.9636";
    public static String lon = "28.1378";
    public static String city = "midrand";
    public static String units = "metric";
    String[] namesOfDays = {
            "SAT","SUN","MON", "TUE", "WED", "THU", "FRI",
    };
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        getCurrentData();
        getDayOfTheWeek();
    }

    public void setupViews() {
        weatherInDegrees = findViewById(R.id.textView_weather_in_degrees);
        weatherDescription = findViewById(R.id.textView_weather_description);
        textViewMin = findViewById(R.id.min_degrees_value);
        textViewCurrent = findViewById(R.id.current_degrees_value);
        textViewMax = findViewById(R.id.max_degrees_values);
        recyclerView = findViewById(R.id.recyclerview_displaying_weather);
        constraintLayout = findViewById(R.id.top_constraint);
        setupRecycler();
    }


    private void populateData() {
        //String weatherDay, int weatherIcon, String weatherInDegrees
        Date newDate  = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String dates =(simpleDateformat.format(newDate));
        weatherModel = new WeatherModel(dates,R.drawable.rain,"30 \"°\"");
        list.add(weatherModel);
        setupAdapter(list);
    }


    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        populateData();
    }



    private void setupAdapter(ArrayList<WeatherModel> list) {
        weatherAdapter = new WeatherAdapter(list);
        recyclerView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.sunny));
        recyclerView.setAdapter(weatherAdapter);
        weatherAdapter.notifyDataSetChanged();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    public void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat,lon,AppId );
        // Call<WeatherResponse> call = service.getCurrentDay(city, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                BufferedReader bufferedReader;
                if (response.code() == 200) {
                    //bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))
                    WeatherResponse weatherResponse = response.body();
                    //assert weatherResponse != null;
                    for (int x = 0; x < weatherResponse.weather.size(); x++) {
                        String tokenWeather = "" + weatherResponse.weather.get(x).description;
                        if (tokenWeather.contains("clear sky")) {
                            weatherDescription.setText(tokenWeather);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_sunnypng));

                        }else if (tokenWeather.contains("broken clouds")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_cloudy));
                        }else if (tokenWeather.contains("few clouds")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_cloudy));
                        }else if (tokenWeather.contains("overcast")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_cloudy));
                        }else if (tokenWeather.contains(" scattered clouds")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_cloudy));
                        }else if (tokenWeather.contains("light rain")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_rainy));
                        }else if (tokenWeather.contains("light rain")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_rainy));
                        }else if (tokenWeather.contains("sunny")) {
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.sea_sunnypng
                            ));
                        }

                    }
                    //String replaceSpace =stringBuilder.replace("\n","");
                    String tokenMin = "" + weatherResponse.main.temp_min + "°";
                    String tokenMax = "" + weatherResponse.main.temp_max + "°";
                    String tokenCurrent = "" + weatherResponse.main.pressure + "°";
                    String temp = "" + weatherResponse.main.temp + "°";
                    String newDate = "" + weatherResponse.dt_txt;
                    textViewMin.setText(tokenMin);
                    textViewMax.setText(tokenMax);
                    textViewCurrent.setText(tokenCurrent);
                    weatherInDegrees.setText(temp);

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherDescription.setText(t.getMessage());
            }
        });
    }

    public void getDayOfTheWeek() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentDay(city,AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    for (int i = 1; i < weatherResponse.weather.size(); i++) {

                        String newDate = "2020-03-05 15:00:00";
                        String icon = weatherResponse.weather.get(i).icon;
                        String temparature = ""+weatherResponse.main.temp + "°";;
                        newDate = namesOfDays[(calendar.get(Calendar.DAY_OF_WEEK)+i)%7];


                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                System.out.println("Error "+ t.getMessage());
            }
        });


    }
}
