package com.sample.contact.demo.mvp.application;

import android.app.Application;

import com.sample.contact.demo.mvp.utils.AppManager;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppManager.getInstance(this);
    }
}
