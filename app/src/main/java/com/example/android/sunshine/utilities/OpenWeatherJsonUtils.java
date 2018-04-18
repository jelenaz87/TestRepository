/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.utilities;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.retrofit.WeatherObject;
import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.data.WeatherContract;

import org.json.JSONException;

import java.net.HttpURLConnection;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {

    /* Location information */
    public static final String OWM_CITY = "city";
    public static final String OWM_COORD = "coord";

    /* Location coordinate */
    public static final String OWM_LATITUDE = "lat";
    public static final String OWM_LONGITUDE = "lon";

    /* Weather information. Each day's forecast info is an element of the "list" array */
    public static final String OWM_LIST = "list";

    public static final String OWM_PRESSURE = "pressure";
    public static final String OWM_HUMIDITY = "humidity";
    public static final String OWM_WINDSPEED = "speed";
    public static final String OWM_WIND_DIRECTION = "deg";

    /* All temperatures are children of the "temp" object */
    public static final String OWM_TEMPERATURE = "temp";

    /* Max temperature for the day */
    public static final String OWM_MAX = "max";
    public static final String OWM_MIN = "min";

    public static final String OWM_WEATHER = "weather";
    public static final String OWM_WEATHER_ID = "id";

    public static final String OWM_MESSAGE_CODE = "cod";


    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *

     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ContentValues[] getWeatherContentValuesFromJson(final Context context, WeatherObject weatherObject)
            throws JSONException {

     //   JSONObject forecastJson = new JSONObject(forecastJsonStr);

        /* Is there an error? */
        if ((Integer)weatherObject.getCode() != null) {
            int errorCode = weatherObject.getCode();

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }






//        JSONArray jsonWeatherArray = forecastJson.getJSONArray(OWM_LIST);
//
//        JSONObject cityJson = forecastJson.getJSONObject(OWM_CITY);
//
//        JSONObject cityCoord = cityJson.getJSONObject(OWM_COORD);
//        double cityLatitude = cityCoord.getDouble(OWM_LATITUDE);
//        double cityLongitude = cityCoord.getDouble(OWM_LONGITUDE);


        SunshinePreferences.setLocationDetails(context, weatherObject.getCityObject().getCoordinate().getCityLatitude(), weatherObject.getCityObject().getCoordinate().getCityLongitude());

        ContentValues[] weatherContentValues = new ContentValues[weatherObject.getList().size()/8];

        /*
         * OWM returns daily forecasts based upon the local time of the city that is being asked
         * for, which means that we need to know the GMT offset to translate this data properly.
         * Since this data is also sent in-order and the first day is always the current day, we're
         * going to take advantage of that to get a nice normalized UTC date for all of our weather.
         */
//        long now = System.currentTimeMillis();
//        long normalizedUtcStartDay = SunshineDateUtils.normalizeDate(now);

        long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
        int y = 0;
        for (int i = 0; i < weatherObject.getList().size(); i++) {
//
            long dateTimeMillis;
            double pressure;
            int humidity;
            double windSpeed;
            double windDirection;

            double high, highInCelsius;
            double low, lowInCelsius;

            int weatherId;
//
//            /* Get the JSON object representing the day */
//            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);
//
//            /*
//             * We ignore all the datetime values embedded in the JSON and assume that
//             * the values are returned in-order by day (which is not guaranteed to be correct).
//             */
            dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * y;
//
            pressure = weatherObject.getList().get(i).getTemperatureObject().getPressure();
            humidity = weatherObject.getList().get(i).getTemperatureObject().getHumidity();
            windSpeed = weatherObject.getList().get(i).getWindInfo().getWindSpeed();
            windDirection = weatherObject.getList().get(i).getWindInfo().getWindDirection();
//
//            /*
//             * Description is in a child array called "weather", which is 1 element long.
//             * That element also contains a weather code.
//             */
//            JSONObject weatherObject =
//                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
//
            weatherId = weatherObject.getList().get(i).getWeatherDetail().get(0).getWeatherId();
//
//            /*
//             * Temperatures are sent by Open Weather Map in a child object called "temp".
//             *
//             * Editor's Note: Try not to name variables "temp" when working with temperature.
//             * It confuses everybody. Temp could easily mean any number of things, including
//             * temperature, temporary variable, temporary folder, temporary employee, or many
//             * others, and is just a bad variable name.
//             */
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            high = weatherObject.getList().get(i).getTemperatureObject().getMaxTemperature();
            highInCelsius = SunshineWeatherUtils.formatInCelsius(high);
            low = weatherObject.getList().get(i).getTemperatureObject().getMinTemperature();
            lowInCelsius = SunshineWeatherUtils.formatInCelsius(low);

            ContentValues weatherValues = new ContentValues();
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DATE, dateTimeMillis);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, humidity);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, pressure);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, windSpeed);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, windDirection);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, highInCelsius);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, lowInCelsius);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, weatherId);

            weatherContentValues[y] = weatherValues;
            y++;
            i = i+7;
        }
    return weatherContentValues;
    }
}