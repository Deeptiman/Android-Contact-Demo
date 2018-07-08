package com.sample.contact.demo.mvp.usecase;

import android.util.Log;

import com.sample.contact.demo.mvp.contactprovider.IContactProvider;
import com.sample.contact.demo.mvp.model.ContactModel;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactUseCase extends UseCase<ContactUseCase.RequestValues, ContactUseCase.ResponseValues> {

    private IContactProvider mIContactProvider;

    public ContactUseCase(IContactProvider iContactProvider) {
        this.mIContactProvider = iContactProvider;
    }

    @Override
    public boolean validateRequest(RequestValues requestValues) {

        if (requestValues == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateResponse(ResponseValues responseValues) {
        return false;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        Log.d("ContactDemo", "ContactUseCase : executeUseCase");

        if (!validateRequest(requestValues)) {
            getUseCaseCallback().onError();
            return;
        }
        Log.d("ContactDemo", "ContactUseCase : getContact");
        mIContactProvider.getContact(new IContactProvider.ContactCallBack() {
            @Override
            public void onContactFound(ContactModel contactModel) {

                if (contactModel == null) {
                    getUseCaseCallback().onError();
                } else {
                    Log.d("ContactDemo", "ContactUseCase : onSuccess");
                    getUseCaseCallback().onSuccess(new ResponseValues(contactModel));
                }
            }

            @Override
            public void onError() {
                Log.d("ContactDemo", "ContactUseCase : onError");
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValues implements UseCase.ResponseValue {
        private ContactModel contactModel;

        public ResponseValues(ContactModel contactModel) {
            this.contactModel = contactModel;
        }

        public ContactModel getContactModel() {
            return contactModel;
        }
    }
}
