package com.sample.contact.demo.mvp.contactprovider;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class RepositoryProvider {

    public static IContactProvider provideContactRepository(AppCompatActivity activity){

        return new ContactProvider(activity,new ContactDataSource(activity));
    }
}
