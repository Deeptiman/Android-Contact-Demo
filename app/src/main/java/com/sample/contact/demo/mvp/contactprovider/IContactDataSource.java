package com.sample.contact.demo.mvp.contactprovider;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IContactDataSource {
    void loadContact(ContactHelper.IGetContacts iGetContacts);
    ArrayList<ContactModel> getContact();
}
