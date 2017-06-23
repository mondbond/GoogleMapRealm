package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.data.AsyncGeneratorTask;
import com.example.mond.googlemaprealm.model.DbMarkerDao;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.view.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

public class MapPresenterImpl implements MapPresenter, DbMarkerDao.MarkerChangeDAOListener,
        AsyncGeneratorTask.OnGeneratedMarkersSaved {

    private MapView mView;
    private DbMarkerDao mDbMarkerDao;

    public MapPresenterImpl(DbMarkerDao helper) {
        mDbMarkerDao = helper;
    }

    @Override
    public void registerView(MapView view) {
        mView = view;
    }


    @Override
    public void unRegisterView() {
        mView = null;
    }

    @Override
    public void addNewMarker(Marker marker) {
        mDbMarkerDao.addNewMarker(marker);
        mDbMarkerDao.getAllMarkers(this);
    }

    @Override
    public void generateMarkers(int count, int radius, LatLng currentLatLng) {
        AsyncGeneratorTask task = new AsyncGeneratorTask(currentLatLng, radius, count, this);
        task.execute();
    }

    @Override
    public void setUpAllMarkers() {
        mDbMarkerDao.getAllMarkers(this);
    }

    @Override
    public void setMarkers(List<Marker> markers) {
        if (mView != null) {
            mView.setAllMarkers(markers);
        }
    }

    @Override
    public void onDbChangeTransactionFinished() {
        setUpAllMarkers();
    }

    @Override
    public void onMarkersReceived(List<Marker> markers) {
        setMarkers(markers);
    }

    @Override
    public void onMarkerListCreated(List<Marker> markers) {
        mDbMarkerDao.addMarkers(markers);
        mDbMarkerDao.getAllMarkers(this);
    }
}
