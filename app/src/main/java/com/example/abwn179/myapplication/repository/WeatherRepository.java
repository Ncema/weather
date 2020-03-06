package com.example.abwn179.myapplication.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.abwn179.myapplication.ApiGateway;
import com.example.abwn179.myapplication.Constance;
import com.example.abwn179.myapplication.WeatherResponse;
import com.example.abwn179.myapplication.core.Content;
import com.example.abwn179.myapplication.service.WeatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private WeatherService weatherService;
    private static WeatherRepository instance;
    private static WeatherRepository weatherRepository;
    private MutableLiveData<WeatherResponse> weatherForecastMutableData  = new MutableLiveData<>();
    private List<WeatherResponse> forecastResponseModels;
    private List<WeatherResponse>currentResponseModels;
    private WeatherResponse weatherResponse;
    private MutableLiveData<Content> networkFailure;
    public static String AppId = "9c2cbef044b0f5e8d06942b5dd970d22";
    public static String lat = "-25.9636";
    public static String lon = "28.1378";
    public static String city = "midrand";

    public static WeatherRepository getInstance(Context context) {
        if (instance == null) instance = new WeatherRepository(context);
        return instance;
    }

    public WeatherRepository(Context context){
        weatherService = ApiGateway.getWeatherData();
        weatherForecastMutableData = new MutableLiveData<>();
        networkFailure = new MutableLiveData<>();
    }


    public void getCurrentData(String lat,String lon, String AppId ){
        Call<WeatherResponse> call = weatherService.getCurrentWeatherData(lat,lon,AppId );
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.code()==200){
                    weatherForecastMutableData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));
            }
        });
    }


    public void getCurrentDay(String city, String appID){
        Call<WeatherResponse> getDayWeather = weatherService.getCurrentDay(city,appID);
        getDayWeather.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.code()==200){
                    weatherForecastMutableData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));
            }
        });
    }




    public MutableLiveData<WeatherResponse>getWeatherLiveData(){
        return weatherForecastMutableData;
    }
}
