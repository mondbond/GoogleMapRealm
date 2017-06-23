package com.example.mond.googlemaprealm.presenters;

import android.util.Log;

import com.example.mond.googlemaprealm.model.DbMarkerDao;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.view.DetailView;

import javax.inject.Inject;

public class DetailMarkerPresenterImpl implements BasePresenter<DetailView>, DetailMarkerPresenter {
    private DetailView mView;
    private DbMarkerDao mDbMarkerDao;

    @Inject
    public DetailMarkerPresenterImpl(DbMarkerDao helper) {
        mDbMarkerDao = helper;
    }

    @Override
    public void registerView(DetailView view) {
        mView = view;
    }

    @Override
    public void unRegisterView() {
        mView = null;
    }

    public void getMarkerById(String id) {
        mDbMarkerDao.getMarkerById(id, new DbMarkerDao.MarkerDAOReceiver() {
            @Override
            public void onMarkerReceived(Marker marker) {
                mView.setMarkerInfo(marker);
            }
        });
    }

    public void updateMarkerById(String id, String title, int index) {
        mDbMarkerDao.updateMarker(id, title, index);
    }

    public void deleteMarkerById(String id) {
        mDbMarkerDao.deleteMarker(id);
    }
}
