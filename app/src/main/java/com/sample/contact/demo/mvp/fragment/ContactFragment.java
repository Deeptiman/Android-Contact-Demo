package com.sample.contact.demo.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.contact.demo.R;
import com.sample.contact.demo.mvp.model.ContactModel;
import com.sample.contact.demo.mvp.presenter.ContactPresenter;
import com.sample.contact.demo.mvp.presenter.IContactContract;

import java.util.ArrayList;


public class ContactFragment extends MFragment implements IContactContract.View {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private IContactContract.Presenter mPresenter;
    private ArrayList<ContactModel> contactModels;
    private ContactViewAdapter contactViewAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactFragment newInstance(ContactPresenter contactPresenter) {
        ContactFragment contactFragment = new ContactFragment();
        contactFragment.setPresenter(contactPresenter);
        return contactFragment;
    }

    private void setPresenter(IContactContract.Presenter presenter){
        mPresenter = presenter;
        super.setPresenter(mPresenter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactmodel_list, container, false);

        // Set the adapter

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        SearchView searchView = (SearchView) view.findViewById(R.id.search);


        contactModels = new ArrayList<>();

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        contactViewAdapter = new ContactViewAdapter(getActivity(), contactModels);
        recyclerView.setAdapter(contactViewAdapter);

        mPresenter.getContacts();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                contactViewAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return view;
    }

    ArrayList<ContactModel> updatedContactModels = new ArrayList<>();
    public void setContactModels(ArrayList<ContactModel> contactModelList){
        updatedContactModels.clear();
        updatedContactModels.addAll(contactModelList);
    }

    public ArrayList<ContactModel> getContactModels(){
        return contactModels;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showContacts(ContactModel contactModel) {
        //
        contactModels.add(contactModel);
        contactViewAdapter.notifyDataSetChanged();

    }

}
