package com.sample.contact.demo.mvp.presenter;

import com.sample.contact.demo.mvp.model.ContactModel;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IContactContract {

    interface View extends IView {
        void showContacts(ContactModel contactModel);
    }
    interface Presenter extends IPresenter {
        void getContacts();
    }
}
