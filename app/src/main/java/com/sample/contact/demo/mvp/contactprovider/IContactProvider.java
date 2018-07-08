package com.sample.contact.demo.mvp.contactprovider;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IContactProvider {

    void getContact(ContactCallBack contactCallBack);

    interface ContactCallBack {
        void onContactFound(ContactModel contactModel);

        void onError();
    }
}
