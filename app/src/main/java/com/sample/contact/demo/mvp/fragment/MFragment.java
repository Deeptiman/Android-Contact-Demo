package com.sample.contact.demo.mvp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.sample.contact.demo.mvp.presenter.IPresenter;
import com.sample.contact.demo.mvp.presenter.IView;

public abstract class MFragment<T extends IPresenter> extends Fragment {
    private static final String TAG = MFragment.class.getSimpleName();
    private T mPresenter;
    private boolean mIsDestroyedbyUser;

    //Setter and Getter for Presenter
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.loadCachedData();
        }
    }

    public void setPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter != null) {
            mPresenter.attachView((IView) this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsDestroyedbyUser = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            if (mIsDestroyedbyUser) {
                mPresenter.detachView(false);
            } else {
                mPresenter.detachView(true);
            }
        }
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void loadingComplete() {
        postLoadedEventToActivity();
    }

    public void showProgress(boolean isShow) {
        if (getActivity() != null) {

        }
    }

    public void showError() {

    }

    private void postLoadedEventToActivity() {

    }
}