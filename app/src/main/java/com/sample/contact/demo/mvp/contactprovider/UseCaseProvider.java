package com.sample.contact.demo.mvp.contactprovider;

import android.support.v7.app.AppCompatActivity;

import com.sample.contact.demo.mvp.usecase.ContactUseCase;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class UseCaseProvider {

    public static ContactUseCase providerContactProviderUseCase(AppCompatActivity activity){
        return new ContactUseCase(RepositoryProvider.provideContactRepository(activity));
    }

}
