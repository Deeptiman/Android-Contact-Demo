package com.sample.contact.demo.mvp.presenter;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IView<T> {
    void loadingComplete();

    void showProgress(boolean isShow);

    void showError();
}