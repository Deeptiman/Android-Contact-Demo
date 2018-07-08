package com.sample.contact.demo.mvp.presenter;

/**
 * Created by AwesomePC on 08-Jul-18.
 */
public interface IPresenter<V extends IView> {
    public void attachView(V view);

    public void detachView(boolean isDestroyedBySystem);

    public void loadCachedData();
}