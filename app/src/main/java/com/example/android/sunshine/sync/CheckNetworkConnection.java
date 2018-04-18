package com.example.android.sunshine.sync;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.example.android.sunshine.R;
import com.example.android.sunshine.data.Annotation;
import com.example.android.sunshine.ui_component.MainActivity;

import javax.inject.Inject;

/**
 * Created by jelena.zivanovic on 12/11/2017.
 */

public class CheckNetworkConnection {

   private ConnectivityManager mConnectivityManager;
   private NetworkInfo mNetworkInfo;
   private Context mContext;



   public CheckNetworkConnection (Context context) {
       this.mContext = context;
       mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
       mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();


   }
   @Annotation
   public boolean isNetworkConnectionAvailable () {
       if (mNetworkInfo != null) {
           new SunshineSyncUtils().initialize(mContext);
           return true;
       } else {
           SunshineDatabaseOperations operations = new SunshineDatabaseOperations(mContext);

         if (operations.checkContentOfDatabase()) {
          return true;
       } else {
             return  false;
         }
   }
    }
}
