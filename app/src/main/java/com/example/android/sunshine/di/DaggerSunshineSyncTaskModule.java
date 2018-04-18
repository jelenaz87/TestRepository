package com.example.android.sunshine.di;

import android.content.Context;

import com.example.android.sunshine.sync.SunshineSyncTask;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/19/2017.
 */
@Module
public class DaggerSunshineSyncTaskModule {

    private Context mContext;

    public DaggerSunshineSyncTaskModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    SunshineSyncTask getSunshineSyncTask () {
        return new SunshineSyncTask(mContext);
    }
}
