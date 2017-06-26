package com.example.mond.googlemaprealm.common;

public interface BasePresenter<T> {

    void attachView(T view);
    void detachView();
}
