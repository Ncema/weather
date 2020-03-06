package com.example.abwn179.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("weather")
    public ArrayList<Weather> weather = new ArrayList<Weather>();
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("rain")
    public Rain rain;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public float dt;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public float cod;
    @SerializedName("dt_txt")
    public String dt_txt;



}

class Weather {
    @SerializedName("id")
    public int id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;
}

class Clouds {
    @SerializedName("all")
    public float all;
}

class Rain {
    @SerializedName("3h")
    public float h3;
}

class Wind {
    @SerializedName("speed")
    public float speed;
    @SerializedName("deg")
    public float deg;


}

class Main {
    @SerializedName("temp")
    public float temp;
    @SerializedName("feels_like")
    public float feels_like;
    @SerializedName("temp_min")
    public float temp_min;
    @SerializedName("temp_max")
    public float temp_max;
    @SerializedName("pressure")
    public float pressure;
    @SerializedName("sea_level")
    public float sea_level;
    @SerializedName("grnd_leve")
    public float grnd_leve;
    @SerializedName("humidity")
    public float humidity;
    @SerializedName("temp_kf")
    public float temp_kf;

}

class Sys {
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public long sunrise;
    @SerializedName("sunset")
    public long sunset;
    @SerializedName("pod")
    public long pod;
}

class Coord {
    @SerializedName("lon")
    public float lon;
    @SerializedName("lat")
    public float lat;
}
