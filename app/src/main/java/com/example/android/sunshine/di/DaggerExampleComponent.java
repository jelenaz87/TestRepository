package com.example.android.sunshine.di;


import com.example.android.sunshine.ui_component.MainActivity;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/18/2017.
 */
@Component( modules = {DaggerExampleModule.class, DaggerNetworkConnectionModule.class})
public interface DaggerExampleComponent {
   void inject (MainActivity mainActivity);
}
