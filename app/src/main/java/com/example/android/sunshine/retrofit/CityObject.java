package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class CityObject {

    @SerializedName(OpenWeatherJsonUtils.OWM_COORD)
    private CityCoordinate coordinate;

    public CityCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CityCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
