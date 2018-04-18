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
package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;

import com.example.android.sunshine.messages.MessageBus;
import com.example.android.sunshine.messages.MessageEvent;
import com.example.android.sunshine.retrofit.WeatherObject;
import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.retrofit.GetWeatherObject;
import com.example.android.sunshine.retrofit.WeatherObjectResult;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.NotificationUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.Serializable;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SunshineSyncTask implements MessageEvent {

    private final Context context;
    private SunshineDatabaseOperations sunshineDatabaseOperations;
    private SunshineNotifications sunshineNotifications;
    private ContentValues[] weatherValues;




    public SunshineSyncTask(Context context) {
        this.context = context;
        weatherValues = null;
        sunshineDatabaseOperations = new SunshineDatabaseOperations(context);
        sunshineNotifications = new SunshineNotifications(context);
        MessageBus.getInstance().subscriber.register(WeatherObject.class,this);



    }



    /**
     * Performs the network request for updated weather, parses the JSON from that request, and
     * inserts the new weather information into our ContentProvider. Will notify the user that new
     * weather has been loaded if the user hasn't been notified of the weather within the last day
     * AND they haven't disabled notifications in the preferences screen.
     *
     *
     */
    synchronized public void syncWeather() {

        try {


            /*
             * The getUrl method will return the URL that we need to get the forecast JSON for the
             * weather. It will decide whether to create a URL based off of the latitude and
             * longitude or off of a simple location as a String.
             */
            URL weatherRequestUrl = NetworkUtils.getUrl(context);

            String locationQuery = SunshinePreferences.getPreferredWeatherLocation(context);


                GetWeatherObject getWeatherObject = new GetWeatherObject(locationQuery);
                getWeatherObject.getWeather(new WeatherObjectResult() {
                    @Override
                    public void onSucess(WeatherObject object) {

                     //   setContentValues(object);

//                        if (isWeatherNotNullAndLengthNotZero()) {
//
//                            sunshineDatabaseOperations.insertNewValueIntoDatabase(weatherValues);
//                            sunshineNotifications.setSunshineNotification();
//
//               /* If the code reaches this point, we have successfully performed our sync */
//
//                        }
                    }

                    @Override
                    public void onFailure(String response) {

                    }
                });

            /* Use the URL to retrieve the JSON */
                //    String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

            /* Parse the JSON into a list of weather values */

            /*
             * In cases where our JSON contained an error code, getWeatherContentValuesFromJson
             * would have returned null. We need to check for those cases here to prevent any
             * NullPointerExceptions being thrown. We also have no reason to insert fresh data if
             * there isn't any to insert.
             */

        } catch (Exception e) {
            /* Server probably invalid */
            e.printStackTrace();
        }
    }

    private  boolean isWeatherNotNullAndLengthNotZero() {
        return weatherValues != null && weatherValues.length != 0;
    }

    private void setContentValues(WeatherObject object) {
        try {
            weatherValues = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void message(Object o) {
        setContentValues((WeatherObject) o);

                        if (isWeatherNotNullAndLengthNotZero()) {

                            sunshineDatabaseOperations.insertNewValueIntoDatabase(weatherValues);
                            sunshineNotifications.setSunshineNotification();

               /* If the code reaches this point, we have successfully performed our sync */

                        }

        MessageBus.getInstance().subscriber.unRegister(WeatherObject.class,this);
    }


}