package com.sample.contact.demo.mvp.presenter;

import android.util.Log;

import com.sample.contact.demo.mvp.usecase.ContactUseCase;
import com.sample.contact.demo.mvp.usecase.UseCase;
import com.sample.contact.demo.mvp.usecase.UseCaseHandler;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactPresenter implements IContactContract.Presenter {

    private IContactContract.View mView;
    private ContactUseCase mContactUseCase;
    private UseCaseHandler mUseCaseHandler;

    public ContactPresenter(ContactUseCase contactUseCase,UseCaseHandler useCaseHandler){
        this.mContactUseCase = contactUseCase;
        this.mUseCaseHandler = useCaseHandler;
    }

    @Override
    public void getContacts() {

        Log.d("ContactDemo", "GetContacts");

        ContactUseCase.RequestValues requestValues = new ContactUseCase.RequestValues();
        mUseCaseHandler.execute(mContactUseCase, requestValues, new UseCase.UseCaseCallback<ContactUseCase.ResponseValues>() {
            @Override
            public void onSuccess(ContactUseCase.ResponseValues response) {

                if(mView!=null){
                    mView.showContacts(response.getContactModel());
                }

            }

            @Override
            public void onError() {
                Log.d("ContactDemo", "GetContacts : onError = ");
            }
        });
    }

    @Override
    public void attachView(IView view) {
        mView = (IContactContract.View) view;
    }

    @Override
    public void detachView(boolean isDestroyedBySystem) {
        mView = null;
    }

    @Override
    public void loadCachedData() {
        //

    }
}
