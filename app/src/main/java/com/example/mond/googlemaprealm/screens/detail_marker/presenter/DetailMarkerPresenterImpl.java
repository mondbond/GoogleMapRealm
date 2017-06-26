package com.example.mond.googlemaprealm.screens.detail_marker.presenter;

import com.example.mond.googlemaprealm.model.MarkerModel;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerFindListener;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailView;

public class DetailMarkerPresenterImpl implements DetailMarkerPresenter {
    private DetailView mView;
    private MarkerModel mDbMarkerDao;

    public DetailMarkerPresenterImpl(MarkerModel helper) {
        mDbMarkerDao = helper;
    }

    @Override
    public void attachView(DetailView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public void getMarkerById(String id) {
        mDbMarkerDao.getById(id, new MarkerFindListener() {
            @Override
            public void onMarkerFind(Marker marker) {
                mView.setMarkerInfo(marker);
            }
        });
    }

    public void updateMarkerById(String id, String title, int index) {
        Marker updatedMarker = new Marker();
        updatedMarker.setTitle(title);
        updatedMarker.setIconType(index);
        mDbMarkerDao.updateById(id, updatedMarker);
    }

    public void deleteMarkerById(String id) {
        mDbMarkerDao.deleteById(id);
    }
}
