package com.example.mond.googlemaprealm.screens.map.presenter;

import com.example.mond.googlemaprealm.model.MarkerModel;
import com.example.mond.googlemaprealm.data.AsyncGeneratorTask;
import com.example.mond.googlemaprealm.model.AllMarkersFindListener;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.screens.map.view.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapPresenterImpl implements MapPresenter, AllMarkersFindListener,
        AsyncGeneratorTask.OnGeneratedMarkersSaved {

    private MapView mView;
    private MarkerModel mDbMarkerDao;

    public MapPresenterImpl(MarkerModel helper) {
        mDbMarkerDao = helper;
    }

    @Override
    public void attachView(MapView view) {
        mView = view;
    }


    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void addNewMarker(Marker marker) {
        mDbMarkerDao.insert(marker);
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
    public void onMarkersFind(List<Marker> markers) {
        setMarkers(markers);
    }

    @Override
    public void onMarkerListCreated(List<Marker> markers) {
        mDbMarkerDao.addMarkerList(markers);
        mDbMarkerDao.getAllMarkers(this);
    }
}
