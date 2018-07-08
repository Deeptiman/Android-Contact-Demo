package com.sample.contact.demo.mvp.usecase;

import android.support.v7.app.AppCompatActivity;

import com.sample.contact.demo.mvp.contactprovider.UseCaseProvider;
import com.sample.contact.demo.mvp.presenter.ContactPresenter;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class Injection {

    public static ContactPresenter providerContactPresenter(AppCompatActivity activity) {
        return new ContactPresenter(UseCaseProvider.providerContactProviderUseCase(activity), UseCaseHandler.getInstance());
    }
}
