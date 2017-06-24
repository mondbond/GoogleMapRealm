package com.example.mond.googlemaprealm.common;

public interface BasePresenter<T> {
    // TODO: 24/06/17
//    void attachView(T view);
//    void detachView();
    void registerView(T view);
    void unRegisterView();
}
