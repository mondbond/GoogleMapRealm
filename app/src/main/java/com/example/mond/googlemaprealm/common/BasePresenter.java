package com.example.mond.googlemaprealm.common;

public interface BasePresenter<T> {
    void registerView(T view);
    void unRegisterView();
}
