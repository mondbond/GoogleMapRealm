package com.example.mond.googlemaprealm.model.listeners;


import io.realm.RealmModel;

public interface SingleObjectFindListener<T extends RealmModel> {
    void onObjectFind(T t);
}
