package com.example.android.sunshine.retrofit;

import com.example.android.sunshine.utilities.NetworkUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jelena.zivanovic on 12/4/2017.
 */

public interface GetClient {

 //   Call<WeatherObject>getWeatherObject(@Url String url);
    @GET ("forecast?id=792680&appid=88679d8457078f98d550b4e24ac9ac26")
    Call<WeatherObject> getWeatherObject ();




  // Call<WeatherObject> getWeatherObject(@Query(NetworkUtils.QUERY_PARAM) String location,
   //                                     @Query(NetworkUtils.FORMAT_PARAM) String format,
  //                     @Query(NetworkUtils.UNITS_PARAM) String units,
   //                     @Query(NetworkUtils.DAYS_PARAM) String days);
}
