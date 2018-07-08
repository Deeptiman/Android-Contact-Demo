package com.sample.contact.demo.mvp.contactprovider;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sample.contact.demo.mvp.contactloader.ContactLoaderManager;
import com.sample.contact.demo.mvp.contactloader.IContactLoader;
import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactHelper implements IContactLoader {

    private AppCompatActivity mActivity;
    private ContactLoaderManager mContactLoaderManager;
    private IGetContacts mIGetContacts;
    private ArrayList<ContactModel> mContactModel;

    public ContactHelper(AppCompatActivity activity) {
        this.mActivity = activity;
        mContactModel = new ArrayList<>();
        this.mContactLoaderManager = new ContactLoaderManager(mActivity, this);
    }

    public void loadContacts(IGetContacts iGetContacts) {
        this.mIGetContacts = iGetContacts;

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mContactLoaderManager.initLoader();
            }
        });
    }

    @Override
    public void onLoadContacts(ArrayList<ContactModel> contactModel) {
        this.mContactModel.addAll(contactModel);
    }

    public ArrayList<ContactModel> getContactModel() {
        Log.d("ContactDemo","getContactModel == "+mContactModel);
        return mContactModel;
    }

    @Override
    public void onLoadContact(ContactModel contactModel) {
        mIGetContacts.onReceiveContact(contactModel);
    }

    public interface IGetContacts {
        public void onReceiveContact(ContactModel contactModel);
    }
}
