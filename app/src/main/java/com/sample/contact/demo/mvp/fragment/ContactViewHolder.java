package com.sample.contact.demo.mvp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.contact.demo.R;
import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final View mView;

    @BindView(R.id.person_icon_img)
    ImageView mPersonImage;

    @BindView(R.id.name_txt)
    TextView mNameTxt;

    @BindView(R.id.phone_txt)
    TextView mPhoneTxt;

    @BindView(R.id.call_icon_img)
    ImageView mCallImage;

    Activity mActivity;
    ArrayList<ContactModel> mContactModels;

    int pos = 0;

    public ContactViewHolder(Activity activity,ArrayList<ContactModel> contactModels,View view) {
        super(view);

        ButterKnife.bind(this, itemView);

        mActivity = activity;
        mContactModels = contactModels;

        mNameTxt = (TextView) view.findViewById(R.id.name_txt);
        mPhoneTxt = (TextView) view.findViewById(R.id.phone_txt);
        mCallImage = (ImageView) view.findViewById(R.id.call_icon_img);

        mView = view;
        mCallImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        pos = getPosition();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+mContactModels.get(pos).getPhoneNumber()));
        if (ActivityCompat.checkSelfPermission(mActivity,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mActivity.startActivity(callIntent);
    }
}
