package com.example.android.sunshine.di;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.android.sunshine.sync.CheckNetworkConnection;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/19/2017.
 */
@Module
public class DaggerNetworkConnectionModule {

    public DaggerNetworkConnectionModule() {
    }

    @Provides
    CheckNetworkConnection getCheckNetworkConnectionObject (Context mContext) {
        return  new CheckNetworkConnection(mContext);
    }
}
