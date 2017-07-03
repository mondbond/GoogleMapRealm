package com.example.mond.googlemaprealm.model;

import com.example.mond.googlemaprealm.model.listeners.ListFindListener;
import com.example.mond.googlemaprealm.model.specifications.RealmResultSpecification;
import com.example.mond.googlemaprealm.model.specifications.RealmResultsSpecification;
import com.example.mond.googlemaprealm.model.listeners.SingleObjectFindListener;

import java.util.List;

import io.realm.RealmModel;

public interface Repository<T extends RealmModel> {

    void insert(T t);
    void insert(List<T> markerList);

    void update(T t, OnLoadSuccessListener listener);
    void delete(String id, OnLoadSuccessListener listener);

//    I made realization with two query methods for single and list return. Is it a good variant ?
    void query(RealmResultSpecification<T> specification, SingleObjectFindListener<T> listener);
    void queryList(RealmResultsSpecification<T> specification, ListFindListener<T> listener);
}
