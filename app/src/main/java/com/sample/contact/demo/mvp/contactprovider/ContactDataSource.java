package com.sample.contact.demo.mvp.contactprovider;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactDataSource implements IContactDataSource {

    private ContactHelper mContactHelper;

    public ContactDataSource(AppCompatActivity activity){
        this.mContactHelper = new ContactHelper(activity);
    }

    @Override
    public void loadContact(ContactHelper.IGetContacts iGetContacts) {
        mContactHelper.loadContacts(iGetContacts);
    }

    @Override
    public ArrayList<ContactModel> getContact() {
        Log.d("ContactDemo", "ContactDataSource : getContact");
        return mContactHelper.getContactModel();
    }
}
