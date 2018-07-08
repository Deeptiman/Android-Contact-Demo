package com.sample.contact.demo.mvp.contactloader;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IContactLoader {

    public void onLoadContact(ContactModel contactModel);
    public void onLoadContacts(ArrayList<ContactModel> contactModel);
}
