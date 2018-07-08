package com.sample.contact.demo.mvp.fragment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.sample.contact.demo.R;
import com.sample.contact.demo.mvp.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable {

    private ArrayList<ContactModel> mContactModels;
    private ArrayList<ContactModel> mContactFilterList;
    private ValueFilter valueFilter;
    private Activity mActivity;

    public ContactViewAdapter(Activity activity,ArrayList<ContactModel> contactModels) {
        mActivity = activity;
        mContactModels = contactModels;
        mContactFilterList = contactModels;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contactmodel, parent, false);
        return new ContactViewHolder(mActivity,mContactModels,view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        ContactModel contactModel = mContactModels.get(position);

        holder.mNameTxt.setText(contactModel.getName());
        holder.mPhoneTxt.setText(contactModel.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        Log.d("ContactDemo", "getItemCount = " + mContactModels.size());
        return mContactModels.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<ContactModel> filterList = new ArrayList<>();
                for (int i = 0; i < mContactFilterList.size(); i++) {
                    if ((mContactFilterList.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mContactFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mContactFilterList.size();
                results.values = mContactFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mContactModels = (ArrayList<ContactModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
