package com.sample.contact.demo.mvp.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.sample.contact.demo.R;
import com.sample.contact.demo.mvp.fragment.ContactFragment;
import com.sample.contact.demo.mvp.presenter.ContactPresenter;
import com.sample.contact.demo.mvp.usecase.Injection;

public class ContactActivity extends BaseActivity  {

    String TAG = "ContactDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        checkPermission();
    }

    @Override
    public void onPermissionReceived() {
        loadFragment();
    }

    @Override
    public void checkPermission() {
        processPermission();
    }

    public void loadFragment() {

        ContactPresenter contactPresenter = Injection.providerContactPresenter(this);
        ContactFragment contactFragment = ContactFragment.newInstance(contactPresenter);

        if(contactFragment!=null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.contact_frame, contactFragment);
            transaction.commitAllowingStateLoss();
        }
    }
}
