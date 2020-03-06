package com.example.abwn179.myapplication.model;

public class WeatherModel {
    private String weatherDay;
    private int weatherIcon;
    private String weatherInDegrees;

    public WeatherModel(String weatherDay, int weatherIcon, String weatherInDegrees) {
        this.weatherDay = weatherDay;
        this.weatherIcon = weatherIcon;
        this.weatherInDegrees = weatherInDegrees;
    }

    public String getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherInDegrees() {
        return weatherInDegrees;
    }

    public void setWeatherInDegrees(String weatherInDegrees) {
        this.weatherInDegrees = weatherInDegrees;
    }
}
