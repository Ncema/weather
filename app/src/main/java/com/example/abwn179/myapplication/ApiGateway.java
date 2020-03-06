package com.example.abwn179.myapplication;

import com.example.abwn179.myapplication.service.WeatherService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGateway {
    public static WeatherService getWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constance.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherService.class);
    }
}
