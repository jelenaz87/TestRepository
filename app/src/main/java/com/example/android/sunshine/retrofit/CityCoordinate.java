package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class CityCoordinate {

    @SerializedName(OpenWeatherJsonUtils.OWM_LATITUDE)
   private double cityLatitude;

    @SerializedName(OpenWeatherJsonUtils.OWM_LONGITUDE)
   private double cityLongitude;


    public double getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(double cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public double getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(double cityLongitude) {
        this.cityLongitude = cityLongitude;
    }
}
