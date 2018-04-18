package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class Wind {

    @SerializedName(OpenWeatherJsonUtils.OWM_WINDSPEED)
    private double windSpeed;
    @SerializedName(OpenWeatherJsonUtils.OWM_WIND_DIRECTION)
    private double windDirection;

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}
