package com.example.mond.googlemaprealm.model.listeners;

import java.util.List;

import io.realm.RealmModel;

public interface ListFindListener<T extends RealmModel> {
    void onListFind(List<T> markers);
}
