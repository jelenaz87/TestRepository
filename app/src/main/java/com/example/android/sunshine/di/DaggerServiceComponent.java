package com.example.android.sunshine.di;

import android.content.Context;

import com.example.android.sunshine.sync.SunshineFirebaseJobService;
import com.example.android.sunshine.sync.SunshineSyncIntentService;
import com.example.android.sunshine.ui_component.MainActivity;
import com.example.android.sunshine.ui_component.SettingsActivity;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/19/2017.
 */
@Component (modules = {DaggerSunshineSyncTaskModule.class})
public interface DaggerServiceComponent {
    void inject (SunshineSyncIntentService service);
    void inject (SunshineFirebaseJobService mService);


}
