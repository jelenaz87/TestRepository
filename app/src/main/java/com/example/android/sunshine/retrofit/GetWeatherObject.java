package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.messages.MessageBus;
import com.example.android.sunshine.utilities.NetworkUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jelena.zivanovic on 12/6/2017.
 */

public class GetWeatherObject {


    private String locationQuery;

    public GetWeatherObject(String locationQuery) {
        this.locationQuery = locationQuery;

    }

    public void getWeather(final WeatherObjectResult result) {
        Call<WeatherObject>objectCall = ApiClient.getResponse().getWeatherObject();
        objectCall.enqueue(new Callback<WeatherObject>() {
                    @Override
                    public void onResponse(Call<WeatherObject> call, Response<WeatherObject> response) {
                        if (response.isSuccessful()) {
                            WeatherObject weatherObject = response.body();
                           // result.onSucess(weatherObject);
                            MessageBus.getInstance().queue.postMessage(weatherObject);

                        } else {
                            result.onFailure("On response ");
                        }



                    }

                    @Override
                    public void onFailure(Call<WeatherObject> call, Throwable throwable) {

                    }
                });
    }
}
