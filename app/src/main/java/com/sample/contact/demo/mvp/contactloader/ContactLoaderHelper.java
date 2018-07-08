package com.sample.contact.demo.mvp.contactloader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactLoaderHelper extends AsyncTaskLoader<ArrayList<ContactModel>> {

    private Context mContext;
    private ContentResolver mContentResolver;
    private Cursor mCursor;
    private IContactLoader mIContactLoader;
    private ContactModel mContactModel = null;
    private ArrayList<ContactModel> mContactModels;

    public ContactLoaderHelper(Context context, IContactLoader iContactLoader, ContentResolver contentResolver) {
        super(context);
        this.mContext = context;
        this.mIContactLoader = iContactLoader;
        this.mContentResolver = contentResolver;
    }

    @Nullable
    @Override
    public ArrayList<ContactModel> loadInBackground() {
        Log.d("ContactDemo", "ContactLoaderHelper : loadInBackground = ");

        int c = 0;

        mContactModels = new ArrayList<>();

        String phoneNumber = null;
        String email = null;

        Uri CONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EMAIL_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EMAIL_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;


        mCursor = mContentResolver.query(CONTACT_CONTENT_URI, null, null, null, null);

        Log.d("ContactDemo", "ContactLoaderHelper : cursor = " + mCursor);

        if (mCursor != null && mCursor.getCount() > 0) {

            Log.d("ContactDemo", "ContactLoaderHelper : cursor = " + mCursor.getCount());

            while (mCursor.moveToNext()) {

                String contactId = mCursor.getString(mCursor.getColumnIndex(_ID));
                String name = mCursor.getString(mCursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    Cursor phoneCursor = mContentResolver.query(PHONE_CONTENT_URI, null, PHONE_CONTACT_ID + " = ?", new String[]{contactId}, null);
                    if (phoneCursor != null) {
                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        }
                        phoneCursor.close();
                    }
                    Cursor emailCursor = mContentResolver.query(EMAIL_CONTENT_URI, null, EMAIL_CONTACT_ID + " = ?", new String[]{contactId}, null);
                    if (emailCursor != null) {
                        while (emailCursor.moveToNext()) {
                            email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                        }
                        emailCursor.close();
                    }

                    mContactModel = new ContactModel();
                    mContactModel.setContactId(contactId);
                    mContactModel.setName(name);
                    mContactModel.setPhoneNumber(phoneNumber);
                    mContactModel.setEmail(email);

                    mIContactLoader.onLoadContact(mContactModel);

                    mContactModels.add(mContactModel);

                    Log.d("ContactDemo", contactId + " -- " + name + " -- " + email + " -- " + phoneNumber);

                }
            }
        }
        return mContactModels;
    }

    public void deliverResult(ArrayList<ContactModel> contactModels, int contactId, int contactSize) {
        /*if (isReset()) {
            if (contactModel != null) {
                mCursor.close();
            }
        }*/

        mContactModels = contactModels;

        if (isStarted()) {
            super.deliverResult(mContactModels);
        }

        /*if (mContactModel != contactModel) {
            mCursor.close();
        }*/

        if (contactId >= contactSize)
            stop();

    }

    private void stop() {
        mCursor.close();
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mContactModel != null) {
            deliverResult(mContactModels);
        }

        if (takeContentChanged() || mContactModel == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if (mCursor != null) {
            mCursor.close();
        }
        //Log.d(Utils.DATABASE_GALLERY_HELPER,"onReset : ");
        mContactModel = null;
    }

    @Override
    public void onCanceled(ArrayList<ContactModel> contactModels) {
        super.onCanceled(contactModels);
        //Log.d(Utils.DATABASE_GALLERY_HELPER,"onCanceled : ");
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }
}
