package com.sample.contact.demo.mvp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.sample.contact.demo.mvp.utils.AppManager;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int READ_REQUEST_PERMISSIONS = 300;
    private boolean REQUEST_PERMISSION_MODE = false;
    public boolean isPermissionReceived = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void processPermission(){

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this,
                    android.Manifest.permission.READ_CONTACTS)) &&
                    (ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this,
                            android.Manifest.permission.CALL_PHONE))) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) != -1 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != -1) {
                    AppManager.setHasContactPermission(AppManager.SUCCESS);
                    permissionReceived();
                } else {
                    AppManager.setHasContactPermission(AppManager.FAILED);
                    addPermission();
                }

            } else {

                ActivityCompat.requestPermissions(BaseActivity.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.CALL_PHONE}, READ_REQUEST_PERMISSIONS);
            }
        } else {
            permissionReceived();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case READ_REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        AppManager.setHasContactPermission(AppManager.SUCCESS);
                        permissionReceived();
                        break;
                    } else {
                        addPermission();
                        break;
                    }
                }
            }
            break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CONTACTS) != -1 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != -1) {

            if (alertDialog != null)
                alertDialog.dismiss();

            if (REQUEST_PERMISSION_MODE)
                permissionReceived();

            AppManager.setHasContactPermission(AppManager.SUCCESS);

        } else if (AppManager.isHasContactPermission() == AppManager.FAILED) {
             addPermission();
        }
    }

    private void permissionReceived(){
        isPermissionReceived = true;
        onPermissionReceived();
    }

    AlertDialog alertDialog;

    private void addPermission() {
        REQUEST_PERMISSION_MODE = true;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BaseActivity.this);
        alertDialogBuilder.setMessage("Allow ContactApp to access your contacts on your device?");
        alertDialogBuilder.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
        alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public abstract void onPermissionReceived();
    public abstract void checkPermission();

}
