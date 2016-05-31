package vee.apps.myweatherapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by binaryvi on 30/04/2016.
 */
public class Weather {
    @SerializedName("main")
    private WeatherMain weathermain;

    @SerializedName("weather")
    private ArrayList<WeatherItem> listweather = new ArrayList<>();

    @SerializedName("name")
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<WeatherItem> getListweather() {
        return listweather;
    }

    public void setListweather(ArrayList<WeatherItem> listweather) {
        this.listweather = listweather;
    }

    public WeatherMain getWeathermain() {
        return weathermain;
    }

    public void setWeathermain(WeatherMain weathermain) {
        this.weathermain = weathermain;
    }
}
