package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class TemperatureObject {

    @SerializedName("temp_max")
    private double maxTemperature;
    @SerializedName("temp_min")
    private double minTemperature;
    @SerializedName(OpenWeatherJsonUtils.OWM_PRESSURE)
    private double pressure;
    @SerializedName(OpenWeatherJsonUtils.OWM_HUMIDITY)
    private int humidity;

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }
    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

}
