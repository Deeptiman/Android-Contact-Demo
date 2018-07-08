package com.sample.contact.demo.mvp.contactprovider;

import android.support.v7.app.AppCompatActivity;

import com.sample.contact.demo.mvp.model.ContactModel;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactProvider implements IContactProvider {

    private AppCompatActivity mActivity;
    IContactDataSource mIContactDataSource;

    public ContactProvider(AppCompatActivity activity,IContactDataSource iContactDataSource) {
        mActivity = activity;
        mIContactDataSource = iContactDataSource;
    }

    @Override
    public void getContact(final ContactCallBack contactCallBack)  {

        mIContactDataSource.loadContact(new ContactHelper.IGetContacts() {
            @Override
            public void onReceiveContact(ContactModel contactModel) {
                if (contactModel!=null) {
                    contactCallBack.onContactFound(contactModel);
                } else {
                    contactCallBack.onError();
                }
            }
        });
    }
}
