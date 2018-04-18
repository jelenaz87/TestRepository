package com.example.android.sunshine.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jelena.zivanovic on 12/19/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {

}
