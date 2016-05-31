package vee.apps.myweatherapp.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by binaryvi on 30/04/2016.
 */
public class WeatherMain {
    @SerializedName("temp")
    private double temp;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private double humidity;

    @SerializedName("temp_min")
    private double tempMin;

    @SerializedName("temp_max")
    private  double tempMax;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }
}
