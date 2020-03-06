package com.example.abwn179.myapplication.service;

import com.example.abwn179.myapplication.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
   @GET("data/2.5/weather?")
   Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    @GET("data/2.5/forecast?")
    Call<WeatherResponse> getCurrentDay(@Query("q") String city, @Query("APPID")String app_id);
}
