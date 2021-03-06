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

import android.content.Context;
import android.support.annotation.NonNull;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SunshineSyncUtils {




    /*
         * Interval at which to sync with the weather. Use TimeUnit for convenience, rather than
         * writing out a bunch of multiplication ourselves and risk making a silly mistake.
         */
//    private static final int SYNC_INTERVAL_HOURS = 3;
//    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
//    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
//
    public static boolean sInitialized;
//
//    private static final String SUNSHINE_SYNC_TAG = "sunshine-sync";

    /**
     * Schedules a repeating sync of Sunshine's weather data using FirebaseJobDispatcher.
     * @param context Context used to create the GooglePlayDriver that powers the
     *                FirebaseJobDispatcher
     */
  //  static void scheduleFirebaseJobDispatcherSync(@NonNull final Context context) {

//        Driver driver = new GooglePlayDriver(context);
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
//
//        /* Create the Job to periodically sync Sunshine */
//        Job syncSunshineJob = dispatcher.newJobBuilder()
//                /* The Service that will be used to sync Sunshine's data */
//                .setService(SunshineFirebaseJobService.class)
//                /* Set the UNIQUE tag used to identify this Job */
//                .setTag(SUNSHINE_SYNC_TAG)
//                /*
//                 * Network constraints on which this Job should run. We choose to run on any
//                 * network, but you can also choose to run only on un-metered networks or when the
//                 * device is charging. It might be a good idea to include a preference for this,
//                 * as some users may not want to download any data on their mobile plan. ($$$)
//                 */
//                .setConstraints(Constraint.ON_ANY_NETWORK)
//                /*
//                 * setLifetime sets how long this job should persist. The options are to keep the
//                 * Job "forever" or to have it die the next time the device boots up.
//                 */
//                .setLifetime(Lifetime.FOREVER)
//                /*
//                 * We want Sunshine's weather data to stay up to date, so we tell this Job to recur.
//                 */
//                .setRecurring(true)
//                /*
//                 * We want the weather data to be synced every 3 to 4 hours. The first argument for
//                 * Trigger's static executionWindow method is the start of the time frame when the
//                 * sync should be performed. The second argument is the latest point in time at
//                 * which the data should be synced. Please note that this end time is not
//                 * guaranteed, but is more of a guideline for FirebaseJobDispatcher to go off of.
//                 */
//                .setTrigger(Trigger.executionWindow(
//                        SYNC_INTERVAL_SECONDS,
//                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
//                /*
//                 * If a Job with the tag with provided already exists, this new job will replace
//                 * the old one.
//                 */
//                .setReplaceCurrent(true)
//                /* Once the Job is ready, call the builder's build method to return the Job */
//                .build();
//
//        /* Schedule the Job with the dispatcher */
//        dispatcher.schedule(syncSunshineJob);
//    }
    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     *
     * @param context Context that will be passed to other methods and used to access the
     *                ContentResolver
     */
    synchronized public void initialize(@NonNull final Context context) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

      //  DaggerDaggerServiceComponent.builder().daggerSunshineSyncTaskModule(new DaggerSunshineSyncTaskModule(context)).build().inject(context);

        /*
         * Only perform initialization once per app lifetime. If initialization has already been
         * performed, we have nothing to do in this method.
         */
        if (sInitialized) return;

        sInitialized = true;

        /*
         * This method call triggers Sunshine to create its task to synchronize weather data
         * periodically.
         */

        new FirebaseJobDispatcherSync(context).scheduleFirebaseJobDispatcherSync();
        executor.execute(new CheckForEmptyRunnable(context));

        /*
         * We need to check to see if our ContentProvider has data to display in our forecast
         * list. However, performing a query on the main thread is a bad idea as this may
         * cause our UI to lag. Therefore, we create a thread in which we will run the query
         * to check the contents of our ContentProvider.
         */
   //     Thread checkForEmpty = new Thread(new Runnable() {
//            @Override
//            public void run() {


//                if (new SunshineDatabaseOperations(context).checkContentOfDatabase()) {
//                    startImmediateSync(context);
//                }

                /* Make sure to close the Cursor to avoid memory leaks! */
            //    cursor.close();
            }
    //    });

        /* Finally, once the thread is prepared, fire it off to perform our checks. */
     //   checkForEmpty.start();
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     *
     *
     */
//    public static void startImmediateSync(@NonNull final Context context) {
//        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
//        context.startService(intentToSyncImmediately);
//    }


