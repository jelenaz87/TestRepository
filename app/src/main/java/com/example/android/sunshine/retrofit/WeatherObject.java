package com.example.android.sunshine.retrofit;


import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/4/2017.
 */

public class WeatherObject {

        @SerializedName( OpenWeatherJsonUtils.OWM_MESSAGE_CODE)
        private int code;
        @SerializedName(OpenWeatherJsonUtils.OWM_CITY)
         private CityObject cityObject;
        @SerializedName(OpenWeatherJsonUtils.OWM_LIST)
         private ArrayList<WeatherInfo> list = new ArrayList<>();


        public int getCode() {
                return code;
        }

        public void setCode(int code) {
                this.code = code;
        }
        public CityObject getCityObject() {
                return cityObject;
        }

        public void setCityObject(CityObject cityObject) {
                this.cityObject = cityObject;
        }

        public ArrayList<WeatherInfo> getList() {
                return list;
        }

        public void setList(ArrayList<WeatherInfo> list) {
                this.list = list;
        }
}
