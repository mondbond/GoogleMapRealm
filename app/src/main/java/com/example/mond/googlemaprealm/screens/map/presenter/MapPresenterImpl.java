package com.example.mond.googlemaprealm.screens.map.presenter;

import com.example.mond.googlemaprealm.model.Repository;
import com.example.mond.googlemaprealm.data.AsyncGeneratorTask;
import com.example.mond.googlemaprealm.model.listeners.ListFindListener;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.specifications.GetAllResultsSpecification;
import com.example.mond.googlemaprealm.screens.map.view.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapPresenterImpl implements MapPresenter, ListFindListener,
        AsyncGeneratorTask.OnGeneratedMarkersSaved {

    private MapView mView;
    private Repository mDbMarkerDao;

    public MapPresenterImpl(Repository helper) {
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
        mDbMarkerDao.queryList(new GetAllResultsSpecification(), this);
    }

    @Override
    public void generateMarkers(int count, int radius, LatLng currentLatLng) {
        AsyncGeneratorTask task = new AsyncGeneratorTask(currentLatLng, radius, count, this);
        task.execute();
    }

    @Override
    public void setUpAllMarkers() {
        mDbMarkerDao.queryList(new GetAllResultsSpecification(), this);
    }

    @Override
    public void setMarkers(List<Marker> markers) {
        if (mView != null) {
            mView.setAllMarkers(markers);
        }
    }

    @Override
    public void showMarkerDetailInfo(com.google.android.gms.maps.model.Marker marker) {
        mView.startDetailActivity(marker);
    }

    @Override
    public void showLoadingAnimation() {
        mView.showLoadingDialog();
    }

    @Override
    public void hideLoadingAnimation() {
        mView.dismissLoadingDialog();
    }

    @Override
    public void onListFind(List<Marker> markers) {
        setMarkers(markers);
    }

    @Override
    public void onMarkerListCreated(List<Marker> markers) {
        mDbMarkerDao.insert(markers);
        mDbMarkerDao.queryList(new GetAllResultsSpecification(), this);
    }
}
