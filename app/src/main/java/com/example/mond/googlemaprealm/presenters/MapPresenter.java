package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.DbHelper;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.view.MapView;


import javax.inject.Inject;

import io.realm.annotations.Index;

public class MapPresenter implements BasePresenter<MapView> {

    private MapView mView;
    private DbHelper mDbHelper;

    @Inject
    public MapPresenter(DbHelper helper){
        mDbHelper = helper;
    }

    @Override
    public void init(MapView view) {
        mView = view;
    }
}
