package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.android.sunshine.data.Annotation;
import com.example.android.sunshine.data.WeatherContract;

/**
 * Created by danko.misic on 12/7/17.
 */

public class SunshineDatabaseOperations {

    private final Context context;
    /* Get a handle on the ContentResolver to delete and insert data */
    private ContentResolver sunshineContentResolver;

    public SunshineDatabaseOperations(Context context) {
        this.context = context;
        sunshineContentResolver = context.getContentResolver();
    }



    public void insertNewValueIntoDatabase(ContentValues[] weatherValues) {

    /* Delete old weather data because we don't need to keep multiple days' data */
        sunshineContentResolver.delete(
                WeatherContract.WeatherEntry.CONTENT_URI,
                null,
                null);

                /* Insert our new weather data into Sunshine's ContentProvider */
        sunshineContentResolver.bulkInsert(
                WeatherContract.WeatherEntry.CONTENT_URI,
                weatherValues);
    }

    public boolean checkContentOfDatabase () {

                /* URI for every row of weather data in our weather table*/
        Uri forecastQueryUri = WeatherContract.WeatherEntry.CONTENT_URI;

                /*
                 * Since this query is going to be used only as a check to see if we have any
                 * data (rather than to display data), we just need to PROJECT the ID of each
                 * row. In our queries where we display data, we need to PROJECT more columns
                 * to determine what weather details need to be displayed.
                 */
        String[] projectionColumns = {WeatherContract.WeatherEntry._ID};
        String selectionStatement = WeatherContract.WeatherEntry
                .getSqlSelectForTodayOnwards();

                /* Here, we perform the query to check to see if we have any weather data */
        Cursor mCursor = sunshineContentResolver.query(
                forecastQueryUri,
                projectionColumns,
                selectionStatement,
                null,
                null);

      return isDatabaseEmpty(mCursor);


    }

    public  boolean isDatabaseEmpty(Cursor cursor) {

        boolean bool = false;
       if (cursor == null || cursor.getCount() == 0) {
            bool = false;
        } else {
            bool = true;
        }
        cursor.close();
        return bool;


    }
}
