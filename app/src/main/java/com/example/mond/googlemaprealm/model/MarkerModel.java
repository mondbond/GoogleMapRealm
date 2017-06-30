package com.example.mond.googlemaprealm.model;

import com.example.mond.googlemaprealm.model.specifications.RealmResultSpecification;
import com.example.mond.googlemaprealm.model.specifications.RealmResultsSpecification;

import java.util.List;

public interface MarkerModel {

    void insert(Marker marker);
    void insert(List<com.example.mond.googlemaprealm.model.Marker> markerList);

    void update(Marker marker, OnLoadSuccessListener listener);
    void delete(String id, OnLoadSuccessListener listener);

    void query(RealmResultSpecification<Marker> specification, MarkerFindListener listener);
    void queryList(RealmResultsSpecification<Marker> specification, MarkersFindListener listener);
}
