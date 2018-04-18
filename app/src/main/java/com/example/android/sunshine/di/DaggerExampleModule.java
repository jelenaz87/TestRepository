package com.example.android.sunshine.di;

import android.content.Context;

import com.example.android.sunshine.ui_component.ForecastAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/18/2017.
 */
@Module
public class DaggerExampleModule {

    private Context mContext;
    private ForecastAdapter.ForecastAdapterOnClickHandler mClickHandler;


    public DaggerExampleModule (Context mContext, ForecastAdapter.ForecastAdapterOnClickHandler handler) {
        this.mContext = mContext;
        this.mClickHandler = handler;
    }

    @Provides Context getmContext () {
        return  this.mContext;
    }

    @Provides
    ForecastAdapter.ForecastAdapterOnClickHandler getForecastAdapterClickHandler () {
        return  this.mClickHandler;
    }

    @Provides ForecastAdapter createForecastAdaper () {
        return new ForecastAdapter(mContext,mClickHandler);
    }


}
