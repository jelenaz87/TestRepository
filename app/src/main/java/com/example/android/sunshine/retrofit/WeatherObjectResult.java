package com.example.android.sunshine.retrofit;

/**
 * Created by jelena.zivanovic on 12/6/2017.
 */

public interface WeatherObjectResult {

    public void onSucess (WeatherObject object);
    public  void onFailure (String response);
}
