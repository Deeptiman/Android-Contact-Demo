package com.sample.contact.demo.mvp.contactloader;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactLoaderManager implements LoaderManager.LoaderCallbacks<ArrayList<ContactModel>> {

    private AppCompatActivity mActivity;
    private IContactLoader mIContactLoader;
    private int LOADER_ID = 1;
    private ContentResolver mContentResolver;

    public ContactLoaderManager(AppCompatActivity activity,IContactLoader iContactLoader) {
        this.mActivity = activity;
        this.mIContactLoader = iContactLoader;
    }

    public void initLoader() {
        Log.d("ContactDemo", "ContactLoaderManager : initLoader");
        mActivity.getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<ContactModel>> onCreateLoader(int id, Bundle args) {

        Log.d("ContactDemo", "ContactLoaderManager : onCreateLoader");

        mContentResolver = mActivity.getContentResolver();
        return new ContactLoaderHelper(mActivity, mIContactLoader,mContentResolver);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<ContactModel>> loader, ArrayList<ContactModel> contact) {

        Log.d("ContactDemo", "ContactLoaderManager : onLoadFinished = contact = "+contact);

        mActivity.getSupportLoaderManager().destroyLoader(LOADER_ID);
        mIContactLoader.onLoadContacts(contact);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<ContactModel>> loader) {

    }
}
