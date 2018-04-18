package com.example.android.sunshine.sync;

import android.content.Context;
import android.text.format.DateUtils;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NotificationUtils;

/**
 * Created by danko.misic on 12/7/17.
 */

public class SunshineNotifications {


    private Context context;
    /*
         * Finally, after we insert data into the ContentProvider, determine whether or not
         * we should notify the user that the weather has been refreshed.
         */
    private boolean notificationsEnabled ;

    /*
     * If the last notification was shown was more than 1 day ago, we want to send
     * another notification to the user that the weather has been updated. Remember,
     * it's important that you shouldn't spam your users with notifications.
     */
    private long timeSinceLastNotification;


    private  boolean oneDayPassedSinceLastNotification = false;

    public SunshineNotifications(Context context) {
        this.context = context;
        notificationsEnabled = SunshinePreferences.areNotificationsEnabled(context);
        timeSinceLastNotification =SunshinePreferences
                .getEllapsedTimeSinceLastNotification(context);
    }

    public void setSunshineNotification(){
        if (timeSinceLastNotification >= DateUtils.DAY_IN_MILLIS) {
            oneDayPassedSinceLastNotification = true;
        }

                /*
                 * We only want to show the notification if the user wants them shown and we
                 * haven't shown a notification in the past day.
                 */
        if (notificationsEnabled && oneDayPassedSinceLastNotification) {
            NotificationUtils.notifyUserOfNewWeather(context);
        }


    };

}
